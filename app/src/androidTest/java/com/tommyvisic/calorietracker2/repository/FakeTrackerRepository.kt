package com.tommyvisic.calorietracker2.repository

import com.tommyvisic.calorietracker2.tracker.domain.TrackableFood
import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood
import com.tommyvisic.calorietracker2.tracker.domain.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random

class FakeTrackerRepository : TrackerRepository {

    private val trackedFood = mutableListOf<TrackedFood>()
    private val getFoodsForDateFlow = MutableSharedFlow<List<TrackedFood>>(replay = 1)
    var searchResults = listOf<TrackableFood>()
    var shouldReturnError = false

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(searchResults)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackedFood.add(food.copy(id = Random.nextInt()))
        getFoodsForDateFlow.emit(trackedFood)
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackedFood.remove(food)
        getFoodsForDateFlow.emit(trackedFood)
    }

    override fun getFoodsForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDateFlow
    }
}