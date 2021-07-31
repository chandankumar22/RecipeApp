package com.ck.dev.recipesapp.ui.recipe

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.model.GetRecipeResponse
import com.ck.dev.recipesapp.model.Recipe
import com.ck.dev.recipesapp.ui.recipedetails.RecipeDetailActivity
import com.ck.dev.recipesapp.utils.ApiCallState
import com.ck.dev.recipesapp.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe.*
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe), OnRvItemClick {

    private val recipesListViewModel: RecipeViewModel by activityViewModels()

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    companion object {
        fun newInstance() = RecipeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(recipesListViewModel.recipesLiveData, ::handleGetRecipeResponse)
        recipe_list.layoutManager = layoutManager
        recipesListViewModel.getRecipes()
        /* main_container.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
             if (scrollY == 0) {
                 toolbar_back_container.visibility = View.VISIBLE
                 toolbar_main_container.visibility = View.INVISIBLE
             } else {
                 toolbar_back_container.visibility = View.GONE
                 toolbar_main_container.visibility = View.VISIBLE
             }
         }*/
        recipe_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    toolbar_back_container.visibility = View.VISIBLE
                    toolbar_main_container.visibility = View.INVISIBLE
                } else {
                    toolbar_back_container.visibility = View.GONE
                    toolbar_main_container.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun handleGetRecipeResponse(status: ApiCallState<GetRecipeResponse>) {
        when (status) {
            is ApiCallState.Loading -> {

            }
            is ApiCallState.Success -> {
                if (status.errorCode == 200) {
                    status.data?.let {
                        val rv = RecipeAdapter(it, this)
                        recipe_list.adapter = rv
                        /*recommended_list.adapter = RecommendedAdapter(it)
                        recommended_list.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )*/
                    }
                }
            }
            is ApiCallState.Failure -> {
                Toast.makeText(requireContext(),"Something went wrong!! Please try again",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClicked(recipe: Recipe, itemView: View) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            requireActivity(),
            itemView,
            getString(R.string.shared_transition)
        )
        val jobsIntent = Intent(context, RecipeDetailActivity::class.java)
        jobsIntent.putExtra("recipeItem", recipe)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.startActivity(requireContext(), jobsIntent, options.toBundle())
        } else {
            ContextCompat.startActivity(requireContext(), jobsIntent, null)
        }
    }

}