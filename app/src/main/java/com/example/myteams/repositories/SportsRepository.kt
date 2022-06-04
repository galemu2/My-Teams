package com.example.myteams.repositories

import com.example.myteams.data.SportsApi
import com.example.myteams.data.models.Teams
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportsRepository @Inject constructor(
    private val api: SportsApi
) {

    suspend fun getTeams(query: String): Response<Teams> {
        return api.getTeams(
            query = query
        )
    }
}