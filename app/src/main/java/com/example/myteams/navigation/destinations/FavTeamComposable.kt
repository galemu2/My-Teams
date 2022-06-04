package com.example.myteams.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.favTeams.FavTeamScreen
import com.example.myteams.util.Constants

@ExperimentalAnimationApi
fun NavGraphBuilder.favTeamComposable(viewModel: FavTeamsViewModel) {
    composable(
        route = Constants.FAV_TEAMS,
    ) {
        FavTeamScreen(viewModel = viewModel)
    }
}