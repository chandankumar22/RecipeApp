package com.ck.dev.recipesapp.api

import com.ck.dev.recipesapp.model.GetRecipeByIdResponse
import com.ck.dev.recipesapp.model.GetRecipeResponse
import com.ck.dev.recipesapp.utils.ApiCallState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {

    @GET("recipes")
    suspend fun getRecipeList(): Response<GetRecipeResponse>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id:String): Response<GetRecipeByIdResponse>
}