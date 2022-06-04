package com.example.myteams.data

import com.example.myteams.BuildConfig
import com.example.myteams.data.models.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/$API_KEY/"
    }

    @GET("searchteams.php")
    suspend fun getTeams(
        @Query("t")
        query: String,
    ): Response<Teams>

}

// API: https://www.thesportsdb.com/api/v1/json/50130162/searchteams.php?t=Arsenal