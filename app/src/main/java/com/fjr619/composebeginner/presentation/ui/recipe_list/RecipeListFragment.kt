package com.fjr619.composebeginner.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fjr619.composebeginner.presentation.ui.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

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
                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.primary,
                        elevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val keyboardController = LocalSoftwareKeyboardController.current
                            val localFocusManager = LocalFocusManager.current
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
                                    viewModel.newSearch(query)
                                    keyboardController?.hide()
                                    localFocusManager.clearFocus()
                                },
                                textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                            )
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