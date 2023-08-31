package com.tommyvisic.calorietracker2.tracker.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * The get foods for a specified date use case. A simple wrapper around the repository object.
 */
class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}