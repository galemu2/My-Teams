package com.example.myteams.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.teamDetails.TeamHistoryScreen
import com.example.myteams.util.Constants


fun NavGraphBuilder.teamHistoryComposable(
    viewModel: FavTeamsViewModel,
    navController: NavHostController
) {

    composable(
        route = Constants.TEAM_DETAIL,
/*        arguments = listOf(navArgument(
            name = "id"
        ) {
            type = NavType.IntType
        })*/
    ) {
        TeamHistoryScreen(
            viewModel = viewModel,
            navController = navController
        )
    }
}
