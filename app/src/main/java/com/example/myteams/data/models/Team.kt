package com.example.myteams.data.models


data class Team(
    val idLeague: String, // important
    val idTeam: String, // important
    val strDescriptionEN: String,  // important
    val strLeague: String,  // important
    val strSport: String, // important
    val strTeam: String, // important
    val strTeamBadge: String, // important
    val strTeamJersey: String, // important

) {
    override fun toString(): String {
        return "$strTeam: $strSport: $strLeague"
    }
}