package com.example.myteams.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myteams.navigation.destinations.favTeamComposable
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.util.Constants.FAV_TEAMS

@ExperimentalAnimationApi
@Composable
fun SetUpNavigation(
    navController: NavHostController,
    viewModel: FavTeamsViewModel
) {


    val screen = remember(navController) {
        // Screens(navHostController = navController)
    }


    NavHost(
        navController = navController,
        startDestination = FAV_TEAMS
    ) {

        favTeamComposable(viewModel = viewModel)
    }
}