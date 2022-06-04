package com.example.myteams.ui.favTeams

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.myteams.ui.FavTeamsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavTeamScreen(viewModel: FavTeamsViewModel) {


    Scaffold(topBar = {

    }) {
        FavTeamContent(viewModel = viewModel)
    }
}


@Composable
fun FavTeamContent(viewModel: FavTeamsViewModel) {
    // todo handle when search is triggered

    HandleTeamContent(viewModel = viewModel)
}