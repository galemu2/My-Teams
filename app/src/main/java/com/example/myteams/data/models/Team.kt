package com.example.myteams.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myteams.util.Constants
import javax.annotation.Nonnull


@Entity(tableName = Constants.DATABASE_TABLE)
data class Team(
    @PrimaryKey(autoGenerate = false)
    val idTeam: String, // important
    val strDescriptionEN: String? = "",  // important
    val strLeague: String? = "",  // important
    val strSport: String? = "", // important
    @Nonnull
    @ColumnInfo(name = "teamName")
    val strTeam: String = "No Name", // important
    val strTeamBadge: String? = "", // important
    val strTeamJersey: String? = "", // important

/*    val idLeague: String, // important
    val idTeam: String, // important
    val strDescriptionEN: String,  // important
    val strLeague: String,  // important
    val strSport: String, // important
    val strTeam: String, // important
    val strTeamBadge: String, // important
    val strTeamJersey: String, // important*/

) {
    override fun toString(): String {
        return "$strTeam: $strSport: $strLeague"
    }
}