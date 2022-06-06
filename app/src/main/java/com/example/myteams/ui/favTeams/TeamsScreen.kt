package com.example.myteams.ui.favTeams

import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.myteams.ui.SportsTeamViewModel


@ExperimentalMaterial3Api
@Composable
fun FavTeamScreen(
    viewModel: SportsTeamViewModel,
) {

    val searchAppBarOpenState by viewModel.searchAppBarOpenState
    val searchTextState by viewModel.searchTextState


    Scaffold(

        topBar = {
            TeamsAppBar(
                viewModel = viewModel,
                searchAppBarOpenedState = searchAppBarOpenState,
                searchTextState = searchTextState
            )
        }) {
        FavTeamContent(
            viewModel = viewModel,
        )
    }
}


@ExperimentalMaterial3Api
@Composable
fun FavTeamContent(
    viewModel: SportsTeamViewModel,
) {

    HandleTeamContent(
        viewModel = viewModel,
    )
}

