package com.example.myteams.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.ui.favTeams.FavTeamScreen
import com.example.myteams.util.Constants

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
fun NavGraphBuilder.favTeamComposable(
    viewModel: SportsTeamViewModel,
    displayTeamHistory: (String) -> Unit,
) {
    composable(
        route = Constants.FAV_TEAMS,
    ) {
        FavTeamScreen(
            viewModel = viewModel,
            displayTeamHistory =displayTeamHistory,
        )
    }
}