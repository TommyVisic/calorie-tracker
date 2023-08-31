package com.tommyvisic.calorietracker2.tracker.domain

/**
 * This is a simple use case that wraps a "I want to delete food that I already tracked" intent by
 * the user.
 */
class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(food: TrackedFood) {
        repository.deleteTrackedFood(food)
    }
}