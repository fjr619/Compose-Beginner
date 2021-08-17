package com.fjr619.composebeginner.repository

import com.fjr619.composebeginner.domain.model.Recipe

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe
}