package com.tommyvisic.calorietracker2.tracker.domain

/**
 * The search food use case. This comes into play when the user is searching for food to track as
 * eaten.
 */
class SearchFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }

        return repository.searchFood(query.trim(), page, pageSize)
    }
}