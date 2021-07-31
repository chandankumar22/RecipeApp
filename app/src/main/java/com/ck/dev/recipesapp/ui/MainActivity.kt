package com.ck.dev.recipesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.ck.dev.recipesapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupViewpager()
        setBottomNavListeners()
    }

    private fun setBottomNavListeners() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recipe_item -> {
                    main_view_pager.currentItem = 0
                    true
                }
                R.id.search_item -> {
                    main_view_pager.currentItem = 1
                    true
                }
                R.id.keep_item -> {
                    main_view_pager.currentItem = 2
                    true
                }
                R.id.person_item -> {
                    main_view_pager.currentItem = 3
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupViewpager() {
        val adapter = MainViewPagerAdapter(this)
        main_view_pager.adapter = adapter
        main_view_pager.offscreenPageLimit = adapter.itemCount - 1
        main_view_pager.isUserInputEnabled = false
    }
}