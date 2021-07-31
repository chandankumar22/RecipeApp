package com.ck.dev.recipesapp.ui.recipedetails

import android.os.Bundle
import android.util.LayoutDirection
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.model.GetRecipeByIdResponse
import com.ck.dev.recipesapp.model.Material
import com.ck.dev.recipesapp.model.Recipe
import com.ck.dev.recipesapp.ui.recipe.RecipeViewModel
import com.ck.dev.recipesapp.utils.ApiCallState
import com.ck.dev.recipesapp.utils.observe
import com.ck.dev.recipesapp.utils.setRecipeImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.layout_recipe_col_detail.view.*

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private val recipeVm: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        hideViews()
        with(intent.getParcelableExtra("recipeItem") as Recipe?) {
            if (this != null) {
                recipe_name.text = name
                top_container.setRecipeImage(image)
                recipeVm.getRecipesById(id)

            } else {
                progress_bar.visibility = View.GONE
            }
        }
        observe(recipeVm.recipeByIdLiveData,::handleGetRecipeResponse)
    }

    private fun showViews() {
        recipe_desc.visibility = View.VISIBLE
        col_container.visibility = View.VISIBLE
        material_tv.visibility = View.VISIBLE
        materials_rv.visibility = View.VISIBLE
    }

    private fun hideViews() {
        recipe_desc.visibility = View.GONE
        col_container.visibility = View.GONE
        material_tv.visibility = View.GONE
        materials_rv.visibility = View.GONE
    }

    private fun handleGetRecipeResponse(status: ApiCallState<GetRecipeByIdResponse>) {
        when (status) {
            is ApiCallState.Loading -> {
                progress_bar.visibility = View.VISIBLE
            }
            is ApiCallState.Success -> {
                progress_bar.visibility = View.GONE
                if (status.errorCode == 200) {
                    status.data?.let {
                        setDataInViews(it)
                    }
                }
            }
            is ApiCallState.Failure -> {
                progress_bar.visibility = View.GONE
                Toast.makeText(this,"Something went wrong!! Please try again", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setDataInViews(recipe: GetRecipeByIdResponse) {
        showViews()
        with(recipe.details) {
            recipe_desc.text = description
            time_container.recipe_detail_col_title.text = getString(R.string.time)
            time_container.recipe_detail_col_quantity.text = timeValue
            time_container.recipe_detail_col_unit.text = timeUnit

            calorie_container.recipe_detail_col_title.text = getString(R.string.calorie)
            calorie_container.recipe_detail_col_quantity.text = calories.split(" ")[0]
            calorie_container.recipe_detail_col_unit.text = calories.split(" ")[1]

            price_container.recipe_detail_col_title.text = getString(R.string.price)
            price_container.quantity_container.layoutDirection = View.LAYOUT_DIRECTION_RTL

            price_container.recipe_detail_col_quantity.text = "231"
            price_container.recipe_detail_col_unit.text = "$"

            setRecipeMaterialRv(materials)
        }
    }

    private fun setRecipeMaterialRv(materials: List<Material>) {
        materials_rv.adapter = RecipeMaterialsAdapter(materials)
        materials_rv.layoutManager = LinearLayoutManager(this)
    }
}