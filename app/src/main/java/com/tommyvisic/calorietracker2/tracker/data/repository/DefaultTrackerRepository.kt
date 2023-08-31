package com.tommyvisic.calorietracker2.tracker.data.repository

import com.tommyvisic.calorietracker2.tracker.data.local.TrackerDao
import com.tommyvisic.calorietracker2.tracker.data.mapper.toTrackedFoodEntity
import com.tommyvisic.calorietracker2.tracker.data.mapper.toTrackableFood
import com.tommyvisic.calorietracker2.tracker.data.mapper.toTrackedFood
import com.tommyvisic.calorietracker2.tracker.data.remote.OpenFoodApi
import com.tommyvisic.calorietracker2.tracker.domain.TrackableFood
import com.tommyvisic.calorietracker2.tracker.domain.TrackedFood
import com.tommyvisic.calorietracker2.tracker.domain.TrackerRepository
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.time.LocalDate

/**
 * Our implementation of TrackerRepository that is backed by a local database and remote REST api.
 */
class DefaultTrackerRepository(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(query, page, pageSize)
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}