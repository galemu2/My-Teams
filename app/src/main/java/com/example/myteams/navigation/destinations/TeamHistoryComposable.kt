package com.example.myteams.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.ui.teamHistory.TeamHistoryScreen
import com.example.myteams.util.Constants


fun NavGraphBuilder.teamHistoryComposable(
    viewModel: SportsTeamViewModel,
    navController: NavHostController,
    onNavigateBack: () -> Unit
) {

    composable(
        route = "${Constants.TEAM_HISTORY}/{${Constants.TEAM_ID}}",
        arguments = listOf(navArgument(name = Constants.TEAM_ID) {
            type = NavType.StringType
        })
    ) {

        val arg = it.arguments?.getString(Constants.TEAM_ID)

        TeamHistoryScreen(
            viewModel = viewModel,
            navController = navController,
            teamId = arg,
            onNavigateBack = onNavigateBack
        )
    }
}
