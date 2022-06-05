package com.example.myteams.ui.favTeams

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.myteams.ui.FavTeamsViewModel


@ExperimentalMaterial3Api
@Composable
fun FavTeamScreen(
    viewModel: FavTeamsViewModel,
) {

    val searchAppBarOpenState by viewModel.searchAppBarOpenState
    val searchTextState by viewModel.searchTextState

    Scaffold(topBar = {
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


@Composable
fun FavTeamContent(
    viewModel: FavTeamsViewModel,
) {

    HandleTeamContent(
        viewModel = viewModel,
    )
}