package com.ck.dev.recipesapp.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ck.dev.recipesapp.R
import com.ck.dev.recipesapp.ui.keep.KeepFragment
import com.ck.dev.recipesapp.ui.profile.ProfileFragment
import com.ck.dev.recipesapp.ui.recipe.RecipeFragment
import com.ck.dev.recipesapp.ui.search.SearchFragment

class MainViewPagerAdapter(mContext: FragmentActivity) : FragmentStateAdapter(mContext) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.bottom_nav_recipe_menu_title,
            R.string.bottom_search_menu_title,
            R.string.bottom_nav_keep_menu_title,
            R.string.bottom_nav_person_menu_title
        )
    }

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipeFragment.newInstance()
            1 -> SearchFragment.newInstance()
            2 -> KeepFragment.newInstance()
            3 -> ProfileFragment.newInstance()
            else -> null!!
        }
    }
}
