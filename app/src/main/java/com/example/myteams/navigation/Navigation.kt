package com.example.myteams.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myteams.navigation.destinations.favTeamComposable
import com.example.myteams.navigation.destinations.teamHistoryComposable
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.util.Constants
import com.example.myteams.util.Constants.FAV_TEAMS

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun SetUpNavigation(
    navController: NavHostController,
    viewModel: SportsTeamViewModel
) {


    NavHost(
        navController = navController,
        startDestination = FAV_TEAMS
    ) {

        favTeamComposable(
            viewModel = viewModel,
            displayTeamHistory = {
                navController.navigate(route = "${Constants.TEAM_HISTORY}/$it")
            }
        )

        teamHistoryComposable(
            viewModel = viewModel,
        ){
            navController.popBackStack()
         }
    }
}