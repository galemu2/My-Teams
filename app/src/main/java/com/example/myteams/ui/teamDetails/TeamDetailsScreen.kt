package com.example.myteams.ui.teamDetails


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.myteams.data.models.Team
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.favTeams.TeamItem

@Composable
fun TeamHistoryScreen(
    viewModel: FavTeamsViewModel,
    navController: NavHostController
) {


    Scaffold(topBar = {}) {
        //   TeamDetailsContent(team =)

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
        ) {
            Text(
                text = "Team Details Screen",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun TeamDetailsContent(
    modifier: Modifier = Modifier, team: Team, saveSelectedTeam: (Team) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        TeamItem(
            team = team,
            saveSelectedTeam = saveSelectedTeam
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = team.strDescriptionEN
        )
    }

}

