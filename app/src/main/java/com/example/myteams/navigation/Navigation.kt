package com.example.myteams.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myteams.navigation.destinations.favTeamComposable
import com.example.myteams.navigation.destinations.teamDetailsComposable
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.util.Constants
import com.example.myteams.util.Constants.FAV_TEAMS

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun SetUpNavigation(
    navController: NavHostController,
    viewModel: FavTeamsViewModel
) {


    NavHost(
        navController = navController,
        startDestination = FAV_TEAMS
    ) {

        favTeamComposable(
            viewModel = viewModel,
            navController = navController,
            navigateToTeamDetails = {
                navController.navigate(route = Constants.TEAM_DETAIL)
            }
        )

        teamDetailsComposable(
            viewModel = viewModel,
            navController = navController
        )
    }
}