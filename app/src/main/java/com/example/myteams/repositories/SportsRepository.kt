package com.example.myteams.repositories

import android.util.Log
import com.example.myteams.data.SportsApi
import com.example.myteams.data.models.Teams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportsRepository @Inject constructor(
    private val api: SportsApi
) {

    suspend fun getTeams(query: String): Teams {

        Log.d("TAG", "getTeams: starting query")
        return api.getTeams(
            query = query
        )
    }
}