package com.ck.dev.recipesapp.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ck.dev.recipesapp.api.RecipeRepository
import com.ck.dev.recipesapp.model.GetRecipeByIdResponse
import com.ck.dev.recipesapp.model.GetRecipeResponse
import com.ck.dev.recipesapp.utils.ApiCallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val getRecepiesLiveData = MutableLiveData<ApiCallState<GetRecipeResponse>>()
    val recipesLiveData: MutableLiveData<ApiCallState<GetRecipeResponse>> get() = getRecepiesLiveData

    private val getRecipeByIdLiveData = MutableLiveData<ApiCallState<GetRecipeByIdResponse>>()
    val recipeByIdLiveData: MutableLiveData<ApiCallState<GetRecipeByIdResponse>> get() = getRecipeByIdLiveData


    fun getRecipes() {
        viewModelScope.launch {
            try{
                val response = repository.getRecipes()
                getRecepiesLiveData.postValue(response)
            }catch (ex:Exception){
                getRecepiesLiveData.postValue(ApiCallState.Failure(500))
            }

        }
    }

    fun getRecipesById(id:String) {
        viewModelScope.launch {
            try{
                val response = repository.getRecipesById(id)
                recipeByIdLiveData.postValue(response)
            }catch (ex:java.lang.Exception){
                getRecepiesLiveData.postValue(ApiCallState.Failure(500))
            }
        }
    }
}