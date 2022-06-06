package com.example.myteams.ui.teamHistory


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.ui.favTeams.ErrorContent
import com.example.myteams.ui.favTeams.LoadingContent
import com.example.myteams.util.Resource

@Composable
fun TeamHistoryScreen(
    viewModel: SportsTeamViewModel,
    onNavigateBack: () -> Unit
) {

    var teamMatches by viewModel.teamMatches
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
                    teamMatches.data?.results?.let { matches ->
                        TeamHistoryContent(matches = matches)
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



