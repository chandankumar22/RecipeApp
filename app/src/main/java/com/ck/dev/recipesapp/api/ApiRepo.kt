package com.ck.dev.recipesapp.api

import com.ck.dev.recipesapp.model.GetRecipeByIdResponse
import com.ck.dev.recipesapp.model.GetRecipeResponse
import com.ck.dev.recipesapp.utils.ApiCallState
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeService: RecipeService) {

    suspend fun getRecipes(): ApiCallState<GetRecipeResponse> {
        val response = recipeService.getRecipeList()
        return if (response.isSuccessful) {
            ApiCallState.Success(data = response.body() as GetRecipeResponse, response.code())
        } else {
            ApiCallState.Failure(response.code())
        }
    }

    suspend fun getRecipesById(id:String): ApiCallState<GetRecipeByIdResponse> {
        val response = recipeService.getRecipeById(id)
        return if (response.isSuccessful) {
            ApiCallState.Success(data = response.body() as GetRecipeByIdResponse, response.code())
        } else {
            ApiCallState.Failure(response.code())
        }
    }

}