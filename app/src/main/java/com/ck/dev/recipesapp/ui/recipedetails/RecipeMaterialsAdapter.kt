package com.ck.dev.recipesapp.ui.recipedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.model.Material
import kotlinx.android.synthetic.main.list_item_recipe_material.view.*

class RecipeMaterialsAdapter(private var materials: List<Material>) :
    RecyclerView.Adapter<RecipeMaterialsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recipe_material, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = materials.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            with(materials[holder.adapterPosition]) {
                recipe_material_name.text = name
                recipe_material_quantity.text = quantity
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}