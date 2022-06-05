package com.example.myteams.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.favTeams.FavTeamScreen
import com.example.myteams.util.Constants

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
fun NavGraphBuilder.favTeamComposable(
    viewModel: FavTeamsViewModel,
    navController: NavHostController,
) {
    composable(
        route = Constants.FAV_TEAMS,
    ) {
        FavTeamScreen(
            viewModel = viewModel,
        )
    }
}