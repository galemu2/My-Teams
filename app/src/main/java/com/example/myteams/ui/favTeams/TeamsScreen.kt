package com.example.myteams.ui.favTeams

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.myteams.R
import com.example.myteams.data.models.Team
import com.example.myteams.ui.SportsTeamViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun FavTeamScreen(
    viewModel: SportsTeamViewModel,
    displayTeamHistory: (String) -> Unit,
) {
    val context = LocalContext.current
    val unknownError = stringResource(id = R.string.unknown_error)

    val searchAppBarOpenState by viewModel.searchAppBarOpenState
    val searchTextState by viewModel.searchTextState

    var snackBarState by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

    SportsSnackBar(
        snackBarState = snackBarState,
        scaffoldState = scaffoldState,
        updateSnackBarState = {
            snackBarState = it
        },
        unDoAction = {
            if (viewModel.teamTobeDeleted.value != null) {
                viewModel.addFavTeam(viewModel.teamTobeDeleted.value!!)
                viewModel.teamTobeDeleted.value = null
            } else {
                Toast.makeText(context, unknownError, Toast.LENGTH_SHORT).show()
            }
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
            { team ->
                viewModel.teamTobeDeleted.value = team
                snackBarState = true
            },
            displayTeamHistory = displayTeamHistory
        )
    }
}

@Composable
fun SportsSnackBar(
    snackBarState: Boolean,
    updateSnackBarState: (Boolean) -> Unit,
    scaffoldState: ScaffoldState,
    unDoAction: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val deleteTeam = stringResource(id = R.string.delete_team)
    val undo = stringResource(id = R.string.undo)
    if (snackBarState)
        LaunchedEffect(key1 = snackBarState,
            block =
            {
                scope.launch {
                    val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = deleteTeam,
                        actionLabel = undo
                    )

                    delay(300)
                    updateSnackBarState(false)
                    if (snackBarResult == SnackbarResult.ActionPerformed) {

                        unDoAction()
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
    displayTeamHistory: (String) -> Unit,
) {

    HandleTeamContent(
        viewModel = viewModel,
        displaySnackBar = displaySnackBar,
        displayTeamHistory = displayTeamHistory
    )
}

