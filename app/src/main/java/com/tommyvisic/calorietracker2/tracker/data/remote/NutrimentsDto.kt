package com.tommyvisic.calorietracker2.tracker.data.remote

import com.squareup.moshi.Json

/**
 * Nutrient info about a food. Gives carbs, protein, fat, and calories per 100g service.
 */
data class NutrimentsDto(
    @field:Json(name = "carbohydrates_100g")
    val carbsPer100g: Double,
    @field:Json(name = "energy-kcal_100g")
    val energyKcal100g: Double,
    @field:Json(name = "fat_100g")
    val fatPer100g: Double,
    @field:Json(name = "proteins_100g")
    val proteinPer100g: Double
)