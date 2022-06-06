package com.example.myteams.repositories

import com.example.myteams.data.FavTeamsDao
import com.example.myteams.data.models.FavTeam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavTeamsRepository @Inject constructor(
    private val dao: FavTeamsDao
) {


    val getAllFavTeams: Flow<List<FavTeam>> = dao.getAllFavTeams()

    suspend fun addFavTeam(favTeam: FavTeam) {
        dao.insertFavTeam(favTeam = favTeam)
    }

    suspend fun deleteFavTeam(favTeam: FavTeam) {
        dao.deleteFavTeam(favTeam = favTeam)
    }
}