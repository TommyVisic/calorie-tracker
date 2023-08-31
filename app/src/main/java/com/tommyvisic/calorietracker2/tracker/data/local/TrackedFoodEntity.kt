package com.tommyvisic.calorietracker2.tracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A database entity object for eaten food that the user has input into the app
 */
@Entity
data class TrackedFoodEntity(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int,
    @PrimaryKey val id: Int? = null
)