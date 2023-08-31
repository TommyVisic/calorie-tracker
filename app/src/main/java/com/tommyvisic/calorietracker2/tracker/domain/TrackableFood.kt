package com.tommyvisic.calorietracker2.tracker.domain

/**
 * Food returned by the search use case that the user can decide to click on and thus indicate that
 * they've eaten it.
 */
data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatPer100g: Int
)