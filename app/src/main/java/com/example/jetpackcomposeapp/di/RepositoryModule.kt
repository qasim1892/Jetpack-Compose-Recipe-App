package com.ALi.mvvmrecipeapp.di

import com.example.jetpackcomposeapp.network.RecipeService
import com.example.jetpackcomposeapp.network.model.RecipeDtoMapper
import com.example.jetpackcomposeapp.repository.RecipeRepository
import com.example.jetpackcomposeapp.repository.RecipeRepository_Impl
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
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}

