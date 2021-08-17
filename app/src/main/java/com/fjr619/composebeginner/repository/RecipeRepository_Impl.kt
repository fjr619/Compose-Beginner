package com.fjr619.composebeginner.repository

import com.fjr619.composebeginner.domain.model.Recipe
import com.fjr619.composebeginner.network.RecipeService
import com.fjr619.composebeginner.network.model.RecipeDtoMapper

class RecipeRepository_Impl (
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper,
): RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.fromEntityList(recipeService.search(token = token, page = page, query = query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }

}