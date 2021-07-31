package com.ck.dev.recipesapp.model

data class Details(
    val calories: String,
    val description: String,
    val id: String,
    val image: String,
    val materials: List<Material>,
    val name: String,
    val timeUnit: String,
    val timeValue: String
)