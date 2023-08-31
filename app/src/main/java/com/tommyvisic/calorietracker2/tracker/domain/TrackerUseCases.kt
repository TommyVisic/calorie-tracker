package com.tommyvisic.calorietracker2.tracker.domain

/**
 * Just composes the various use cases around the tracker feature so they don't each have to be
 * passed in individually. I'm not sure I really like this but let's see how it feels.
 */
data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)
