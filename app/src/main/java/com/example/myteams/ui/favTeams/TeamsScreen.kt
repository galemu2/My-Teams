package com.example.myteams.ui.favTeams

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.myteams.data.models.Team
import com.example.myteams.ui.SportsTeamViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun FavTeamScreen(
    viewModel: SportsTeamViewModel,
) {

    val searchAppBarOpenState by viewModel.searchAppBarOpenState
    val searchTextState by viewModel.searchTextState

    var snackBarState by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    SportsSnackBar(
        snackBarState = snackBarState,
        scaffoldState = scaffoldState,
        updateSnackBarState = { snackBarState = it }
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
        ) { team ->
            snackBarState = true
//            viewModel.addFavTeam(team)
        }
    }
}

@Composable
fun SportsSnackBar(
    snackBarState: Boolean,
    updateSnackBarState: (Boolean) -> Unit,
    scaffoldState: ScaffoldState,
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if (snackBarState)
        LaunchedEffect(key1 = snackBarState,
            block =
            {
                scope.launch {
                    val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Delete Team",
                        actionLabel = "Undo"
                    )

                    delay(300)
                    updateSnackBarState(false)
                    if (snackBarResult == SnackbarResult.ActionPerformed) {
                        Toast.makeText(context, "snackbar arction", Toast.LENGTH_SHORT).show()
                    }
                }
            })
}

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun FavTeamContent(
    viewModel: SportsTeamViewModel,
    displaySnackBar: (Team) -> Unit,
) {

    HandleTeamContent(
        viewModel = viewModel,
        displaySnackBar = displaySnackBar
    )
}

