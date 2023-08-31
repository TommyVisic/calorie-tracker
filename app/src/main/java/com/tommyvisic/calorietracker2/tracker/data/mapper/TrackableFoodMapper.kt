package com.tommyvisic.calorietracker2.tracker.data.mapper

import com.tommyvisic.calorietracker2.tracker.data.remote.ProductDto
import com.tommyvisic.calorietracker2.tracker.domain.TrackableFood
import kotlin.math.roundToInt

/**
 * Maps the product data transfer object (network layer) to a domain layer TrackableFood object
 */
fun ProductDto.toTrackableFood(): TrackableFood? {
    return TrackableFood(
        name = name ?: return null,
        imageUrl = imageUrl,
        carbsPer100g = nutriments.carbsPer100g.roundToInt(),
        proteinPer100g = nutriments.proteinPer100g.roundToInt(),
        fatPer100g = nutriments.fatPer100g.roundToInt(),
        caloriesPer100g = nutriments.energyKcal100g.roundToInt()
    )
}