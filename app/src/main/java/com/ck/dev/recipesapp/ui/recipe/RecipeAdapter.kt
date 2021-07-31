package com.ck.dev.recipesapp.ui.recipe

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.model.Recipe
import com.ck.dev.recipesapp.ui.recipedetails.RecipeDetailActivity
import com.ck.dev.recipesapp.utils.setRecipeImage
import kotlinx.android.synthetic.main.list_item_recepie.view.*

class RecipeAdapter(private var recipeList: List<Recipe>,private val rvCallback:OnRvItemClick) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recepie, parent, false)
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
                setOnClickListener {
                    rvCallback.onItemClicked(this,this@apply)
                }
            }
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnRvItemClick{
    fun onItemClicked(recipe:Recipe,itemView: View)
}