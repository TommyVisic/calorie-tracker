package com.tommyvisic.calorietracker2.tracker.data.mapper

import com.tommyvisic.calorietracker2.tracker.data.local.TrackedFoodEntity
import com.tommyvisic.calorietracker2.tracker.domain.MealType
import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood
import java.time.LocalDate

/**
 * Maps a data layer entity object to a domain layer TrackedFood object.
 */
fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

/**
 * Maps a domain layer TrackedFood object to a data layer entity object.
 */
fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}