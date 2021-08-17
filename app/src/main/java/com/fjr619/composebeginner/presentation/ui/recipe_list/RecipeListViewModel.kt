package com.fjr619.composebeginner.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.fjr619.composebeginner.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
) : ViewModel() {
    fun getRepo() = repository
    fun getAuthToken() = token
}