package com.example.myteams.ui.favTeams

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.example.myteams.R
import kotlinx.coroutines.launch

@Composable
fun SportsSnackBar(
    scaffoldState: ScaffoldState,
    snackBarState: Boolean,
    updateSnackBarState: () -> Unit,
    unDoAction: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    val deleteTeam = stringResource(id = R.string.delete_team)
    val undo = stringResource(id = R.string.undo)
    if (snackBarState) {
        LaunchedEffect(
            key1 = snackBarState,
            block = {
                scope.launch {
                    val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = deleteTeam,
                        actionLabel = undo
                    )

                    if (snackBarResult == SnackbarResult.ActionPerformed) {
                        unDoAction()
                    }
                    updateSnackBarState()
                }
            })
    }
}