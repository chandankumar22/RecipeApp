package com.ck.dev.recipesapp.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.model.Recipe
import com.ck.dev.recipesapp.utils.setRecipeImage
import kotlinx.android.synthetic.main.list_item_recommended.view.*

class RecommendedAdapter(
    private var recipeList: List<Recipe>
) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recommended, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            with(recipeList[holder.adapterPosition]) {
                recipe_name.text = name
                setRecipeImage(image)
            }
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}