package com.example.myteams.data

import androidx.room.*
import com.example.myteams.data.models.FavTeam
import kotlinx.coroutines.flow.Flow


@Dao
interface FavTeamsDao {

    @Query("SELECT * FROM fav_team_table")
    fun getAllFavTeams(): Flow<List<FavTeam>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavTeam(favTeam: FavTeam)

    @Delete
    suspend fun deleteFavTeam(favTeam: FavTeam)
}