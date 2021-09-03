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
import androidx.compose.foundation.layout.*
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
import com.fjr619.composebeginner.presentation.ui.components.CircularIndeterminateProgressBar
import com.fjr619.composebeginner.presentation.ui.components.FoodCategoryChip
import com.fjr619.composebeginner.presentation.ui.components.RecipeCard
import com.fjr619.composebeginner.presentation.ui.components.SearchAppbar
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
                val loading = viewModel.loading.value
                Column(modifier = Modifier.background(color = Color.White)) {
                    SearchAppbar(
                        query = query,
                        onQueryChanged = {
                            viewModel.onQueryChanged(it)
                        },
                        onExecuteSearch = {
                            viewModel.newSearch()
                        },
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = {
                            viewModel.onSelectedCategoryChanged(it)
                        },
                        onChangeCategoryScrollPosition = {
                            viewModel.onChangeCategoryScrollPosition(it)
                        }
                    )

                    //seperti framelayout, view tindih2an
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn {
                            itemsIndexed(items = recipes) { index, item ->
                                RecipeCard(recipe = item, onClick = {
                                    Log.e("TAG", "$index ${item.id} ${item.title}")
                                })
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }
}