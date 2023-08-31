package com.tommyvisic.calorietracker2.tracker.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * The repository for trackable and tracked food. Trackable food are items returned from a search
 * that aren't yet clicked by the user. Tracked food is already indicated as eaten and factors into
 * nutrient calculations.
 */
interface TrackerRepository {
    suspend fun searchFood(query: String, page: Int, pageSize: Int): Result<List<TrackableFood>>
    suspend fun insertTrackedFood(food: TrackedFood)
    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(date: LocalDate): Flow<List<TrackedFood>>
}