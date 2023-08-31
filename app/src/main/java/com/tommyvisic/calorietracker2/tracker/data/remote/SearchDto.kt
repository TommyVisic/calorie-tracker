package com.tommyvisic.calorietracker2.tracker.data.remote

/**
 * The actual response data object from a search query on the open food API.
 */
data class SearchDto(
    val products: List<ProductDto>
)
