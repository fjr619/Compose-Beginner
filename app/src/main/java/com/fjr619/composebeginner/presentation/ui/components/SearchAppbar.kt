package com.fjr619.composebeginner.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fjr619.composebeginner.presentation.ui.recipe_list.FoodCategory
import com.fjr619.composebeginner.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun SearchAppbar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Int) -> Unit,
) {
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
                        onQueryChanged(it)
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
                        onExecuteSearch()
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
                    scrollState.scrollTo(scrollPosition)
                }
                for (category in getAllFoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelection = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            Log.e("TAG", "scroll value ${scrollState.value}")
                            onSelectedCategoryChanged(it)
                            onChangeCategoryScrollPosition(scrollState.value)
                        },
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                    )
                }
            }
        }
    }
}