package com.example.myteams.ui.teamHistory


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.myteams.data.models.Team
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.ui.favTeams.ErrorContent
import com.example.myteams.ui.favTeams.LoadingContent
import com.example.myteams.util.Resource

// todo may not be necessary
@Composable
fun TeamHistoryScreen(
    viewModel: SportsTeamViewModel,
    onNavigateBack: () -> Unit
) {

    var teamMatches by remember { viewModel.teamMatches }
    LaunchedEffect(key1 = viewModel.teamMatches, block = {
        teamMatches = viewModel.teamMatches.value
    })

    Scaffold(topBar = {
        TeamHistoryAppBar(
            onNavigateBack = onNavigateBack
        )
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
        ) {

            when (teamMatches) {
                is Resource.Success -> {


                    teamMatches.data?.results?.let {
                        // todo add match UI
                        Text(
                            text = "Team History Screen: ${it[0].strAwayTeam} ",
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center
                        )
                    } ?: ErrorContent()

                }
                is Resource.Error -> {
                    ErrorContent()

                }

                is Resource.Loading -> {
                    LoadingContent()
                }
            }


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

/*        TeamItem(
            team = team,
            viewModel = viewModel
        )*/
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = team.strDescriptionEN ?: ""
        )
    }

}

