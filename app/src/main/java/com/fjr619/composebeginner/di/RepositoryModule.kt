package com.fjr619.composebeginner.di

import com.fjr619.composebeginner.network.RecipeService
import com.fjr619.composebeginner.network.model.RecipeDtoMapper
import com.fjr619.composebeginner.repository.RecipeRepository
import com.fjr619.composebeginner.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    //kenapa yang di return impl, supaya nantinya gampang untuk diganti2 ketika dibutuhkan contoh untuk testing
    //ini cara pertama, untuk cara ke-2 bisa langsung inject di impl nya
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}