package com.example.myteams.data

import androidx.room.*
import com.example.myteams.data.models.Team
import kotlinx.coroutines.flow.Flow


@Dao
interface FavTeamsDao {

    @Query("SELECT * FROM fav_team_table")
    fun getAllFavTeams(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavTeam(favTeam: Team)

    @Delete
    suspend fun deleteFavTeam(favTeam: Team)
}