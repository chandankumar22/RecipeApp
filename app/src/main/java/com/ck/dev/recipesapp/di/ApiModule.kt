package com.ck.dev.recipesapp.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.ck.dev.recipesapp.api.RecipeRepository
import com.ck.dev.recipesapp.api.RecipeService
import com.ck.dev.recipesapp.api.RetrofitFactory
import com.ck.dev.recipesapp.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providesRecipeService(retrofit: RetrofitFactory) = retrofit.getRecepieService(Constants.RECIPE_BASE_URL)

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeService: RecipeService) = RecipeRepository(recipeService)

    @Provides
    @Singleton
    fun provideLinearLayoutManager(@ApplicationContext context:Context) = LinearLayoutManager(context)
}
