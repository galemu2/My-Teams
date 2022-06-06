package com.example.myteams.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myteams.data.models.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class FavTeamsDatabase : RoomDatabase() {
    abstract fun favTeamDao(): FavTeamsDao
}