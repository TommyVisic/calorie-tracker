package com.tommyvisic.calorietracker2.tracker.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Our open food Retrofit API. The concrete type for this gets created as part of dependency
 * injection.
 */
interface OpenFoodApi {

    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): SearchDto

    companion object {
        const val BaseUrl = "https://us.openfoodfacts.org/"
    }
}