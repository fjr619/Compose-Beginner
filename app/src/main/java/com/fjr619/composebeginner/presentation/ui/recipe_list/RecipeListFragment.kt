package com.fjr619.composebeginner.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fjr619.composebeginner.presentation.ui.components.FoodCategoryChip
import com.fjr619.composebeginner.presentation.ui.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                Column(modifier = Modifier.background(color = Color.White)) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val keyboardController = LocalSoftwareKeyboardController.current
                                val localFocusManager = LocalFocusManager.current

                                //https://issuetracker.google.com/issues/192433071
                                //ada issues focus ketika backpress
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    value = query,
                                    onValueChange = {
                                        viewModel.onQueryChanged(it)
                                    },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search,
                                    ),
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search, "Search")
                                    },
                                    keyboardActions = KeyboardActions {
                                        viewModel.newSearch()
                                        keyboardController?.hide()
                                        localFocusManager.clearFocus()
                                    },
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                                )
                            }

                            val scrollState = rememberScrollState()
                            val coroutine = rememberCoroutineScope()
                            
                            Row(
                                modifier = Modifier
                                    .horizontalScroll(scrollState)
                                    .padding(start = 8.dp, bottom = 8.dp),

                            ) {
                                coroutine.launch {
                                    scrollState.scrollTo(viewModel.categoryScrollPosition)
                                }
                                for (category in getAllFoodCategories()) {
                                        FoodCategoryChip(
                                            category = category.value,
                                            isSelection = selectedCategory == category,
                                            onSelectedCategoryChanged = {
                                                Log.e("TAG", "scroll value ${scrollState.value}")
                                                viewModel.onChangeCategoryScrollPosition(scrollState.value)
                                                viewModel.onSelectedCategoryChanged(it)
                                            },
                                            onExecuteSearch = viewModel::newSearch,
                                        )
                                }
                            }
                        }

                    }

                    LazyColumn {
                        itemsIndexed(items = recipes) { index, item ->
                            RecipeCard(recipe = item, onClick = {
                                Log.e("TAG", "$index ${item.id} ${item.title}")
                            })
                        }
                    }
                }
            }
        }
    }
}