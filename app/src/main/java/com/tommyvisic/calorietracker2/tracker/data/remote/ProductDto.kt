package com.tommyvisic.calorietracker2.tracker.data.remote

import com.squareup.moshi.Json

/**
 * Describes a product object that comes from the open food API.
 */
data class ProductDto(
    @field:Json(name = "image_front_thumb_url")
    val imageUrl: String?,
    val nutriments: NutrimentsDto,
    @field:Json(name = "product_name")
    val name: String?
)