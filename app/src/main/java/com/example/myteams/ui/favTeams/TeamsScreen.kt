package com.example.myteams.ui.favTeams

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import com.example.myteams.data.models.Team
import com.example.myteams.ui.SportsTeamViewModel

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun FavTeamScreen(
    viewModel: SportsTeamViewModel,
    displayTeamHistory: (String) -> Unit,
) {

    val searchAppBarOpenState by viewModel.searchAppBarOpenState
    val searchTextState by viewModel.searchTextState

    val scaffoldState = rememberScaffoldState()
    var snackBarState by remember { mutableStateOf(false) }

    SportsSnackBar(
        scaffoldState = scaffoldState,
        snackBarState = snackBarState,
        updateSnackBarState = {
            snackBarState = false
        },
        unDoAction = {
            if (viewModel.teamTobeDeleted.value != null) {
                viewModel.addFavTeam(favTeam = viewModel.teamTobeDeleted.value!!)
                Log.d("TAG", "SportsSnackBar:${viewModel.teamTobeDeleted.value}")
            } else {
                Log.d("TAG", "SportsSnackBar: teamTobeDeleted == null")

            }
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()

        }
    )


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TeamsAppBar(
                viewModel = viewModel,
                searchAppBarOpenedState = searchAppBarOpenState,
                searchTextState = searchTextState
            )
        }) {
        FavTeamContent(
            viewModel = viewModel,
            displaySnackBar = { team ->
                snackBarState = true
                viewModel.teamTobeDeleted.value = team.copy()

            },
            displayTeamHistory = displayTeamHistory,
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun FavTeamContent(
    viewModel: SportsTeamViewModel,
    displaySnackBar: (Team) -> Unit,
    displayTeamHistory: (String) -> Unit,
) {

    HandleTeamContent(
        viewModel = viewModel,
        displaySnackBar = displaySnackBar,
        displayTeamHistory = displayTeamHistory,
    )
}

