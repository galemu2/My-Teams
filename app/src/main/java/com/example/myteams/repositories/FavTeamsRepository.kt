package com.example.myteams.repositories

import com.example.myteams.data.FavTeamsDao
import com.example.myteams.data.models.Team
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavTeamsRepository @Inject constructor(
    private val dao: FavTeamsDao
) {


    var getAllFavTeams: Flow<List<Team>> = dao.getAllFavTeams()

    suspend fun addFavTeam(favTeam: Team) {
        dao.insertFavTeam(favTeam = favTeam)
    }

    suspend fun deleteFavTeam(favTeam: Team) {
        dao.deleteFavTeam(favTeam = favTeam)
    }
}