package com.example.myteams.ui.favTeams

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myteams.R
import com.example.myteams.data.models.Team
import com.example.myteams.data.models.Teams
import com.example.myteams.ui.SportsTeamViewModel
import com.example.myteams.ui.theme.MyTeamsTheme
import com.example.myteams.ui.theme.basicTextColor
import com.example.myteams.ui.theme.contentBackground
import com.example.myteams.ui.theme.displayFavTeamBackground
import com.example.myteams.util.Resource

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun HandleTeamContent(
    viewModel: SportsTeamViewModel,
    displaySnackBar: (Team) -> Unit,
    displayTeamHistory: (String) -> Unit,
) {

    val getFavTeams by viewModel.getFavTeams.collectAsState()

    var teamSearch = remember {
        viewModel.searchTeam
    }

    LaunchedEffect(key1 = viewModel.searchTeam, block = {
        teamSearch = viewModel.searchTeam
    })


    if (viewModel.searchAppBarOpenState.value) {
        // todo show empty search results
        when (teamSearch.value) {
            is Resource.Loading -> {
                EmptySearchContent()
            }
            is Resource.Error -> {
                ErrorContent()
            }
            is Resource.Success -> {
                (teamSearch.value as Resource.Success<Teams>).data?.teams?.let { teams ->


                    DisplaySearchTeams(
                        teams = teams,
                        viewModel = viewModel
                    )
                } ?: EmptySearchContent()
            }
        }
    } else {

        when (getFavTeams) {
            is Resource.Success -> {
                getFavTeams.data?.let { favTeams ->
                    DisplayFavTeams(
                        favTeams = favTeams,
                        viewModel = viewModel,
                        displaySnackBar = displaySnackBar,
                        displayTeamHistory = displayTeamHistory,
                    )
                } ?: EmptyFavesContent()
            }
            is Resource.Loading -> {

                LoadingContent()
            }
            is Resource.Error -> {
                ErrorContent()
            }

        }

    }

}

@ExperimentalMaterialApi
@Composable
fun DisplayFavTeams(
    favTeams: List<Team>,
    viewModel: SportsTeamViewModel,
    displayTeamHistory: (String) -> Unit,
    displaySnackBar: (Team) -> Unit,
) {

    if (favTeams.isEmpty()) {
        EmptyFavesContent()
    } else
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = contentBackground),
        ) {
            items(
                items = favTeams,
                key = { team -> team.idTeam }
            ) { team ->

                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    displaySnackBar(team)
                    viewModel.deleteFavTeam(favTeam = team)

                }


                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(fraction = 0.3f) },
                    background = {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                                .background(Color.DarkGray)
                        )
                    }
                ) {
                    TeamItemFaves(
                        team = team,
                        viewModel = viewModel,
                        displayTeamHistory = displayTeamHistory,

                        )
                }

            }
        }
}


@Composable
fun TeamItemFaves(
    modifier: Modifier = Modifier,
    team: Team,
    viewModel: SportsTeamViewModel,
    displayTeamHistory: (String) -> Unit,

    ) {


    Row(
        modifier = Modifier
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape =
                RoundedCornerShape(4.dp)
            )
            .fillMaxWidth()
            .background(color = displayFavTeamBackground)
            .height(100.dp)
            .clickable {
                displayTeamHistory(team.idTeam)
            },

        ) {

        TeamBadge(
            modifier = Modifier.weight(1f),
            badge = team.strTeamBadge ?: ""
        )

        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(4f),
        ) {
            TeamName(
                modifier = Modifier.weight(2f),
                name = team.strTeam
            )
            SportName(
                modifier = Modifier.weight(1f),
                sportName = team.strSport ?: ""
            )
            LeagueName(
                modifier = Modifier.weight(1f),
                leagueName = team.strLeague ?: ""
            )
        }

        TeamJersey(
            modifier = Modifier.weight(1f),
            teamJersey = team.strTeamJersey ?: ""
        )
    }

}


@Composable
fun DisplaySearchTeams(
    teams: List<Team>,
    viewModel: SportsTeamViewModel,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = contentBackground),
    ) {
        items(
            items = teams,
            key = { team -> team.idTeam }
        ) { team ->
            TeamItemSearch(
                team = team,
                viewModel = viewModel
            )
        }
    }
}


@Composable
fun TeamItemSearch(
    modifier: Modifier = Modifier,
    team: Team,
    viewModel: SportsTeamViewModel,
) {

    var dialogState by remember { mutableStateOf(false) }

    if (dialogState) {
        SelectFavTeamDialog(
            openDialogState = dialogState,
            updateDialogState = { dialogState = it },
        ) {

            viewModel.addFavTeam(favTeam = team)
            viewModel.searchAppBarOpenState.value = false
            viewModel.searchTextState.value = ""
            viewModel.searchTeam.value = Resource.Loading()
        }
    }

    Row(
        modifier = Modifier
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape =
                RoundedCornerShape(4.dp)
            )
            .fillMaxWidth()
            .background(color = displayFavTeamBackground)
            .height(100.dp)
            .clickable {
                dialogState = true
            },

        ) {

        TeamBadge(
            modifier = Modifier.weight(1f),
            badge = team.strTeamBadge ?: ""
        )

        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(4f),
        ) {
            TeamName(
                modifier = Modifier.weight(2f),
                name = team.strTeam
            )
            SportName(
                modifier = Modifier.weight(1f),
                sportName = team.strSport ?: ""
            )
            LeagueName(
                modifier = Modifier.weight(1f),
                leagueName = team.strLeague ?: ""
            )
        }

        TeamJersey(
            modifier = Modifier.weight(1f),
            teamJersey = team.strTeamJersey ?: ""
        )
    }
}

@Composable
fun SelectFavTeamDialog(
    openDialogState: Boolean,
    updateDialogState: (Boolean) -> Unit,
    insertToDatabase: () -> Unit,
) {

    if (openDialogState)
        AlertDialog(
            backgroundColor = displayFavTeamBackground,
            onDismissRequest = {
                updateDialogState(false)
            },
            title = {
                Text(
                    text = stringResource(id = R.string.save_team), color = basicTextColor,
                    fontSize = 30.sp
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.save_selected_team),
                    color = basicTextColor
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    MyButton(
                        updateDialogState = updateDialogState,
                        text = stringResource(id = R.string.cancel)
                    ) {}
                    MyButton(
                        updateDialogState,
                        text = stringResource(id = R.string.select),
                        insertToDatabase = insertToDatabase
                    )
                }
            }
        )
}


@Composable
private fun MyButton(
    updateDialogState: (Boolean) -> Unit,
    text: String,
    insertToDatabase: () -> Unit
) {
    Button(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        onClick = {
            updateDialogState(false)
            insertToDatabase()
        }) {
        Text(text = text, color = basicTextColor, fontWeight = FontWeight.Bold)
    }
}

@Preview(
    showBackground = false,
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewMyButton() {

    MyTeamsTheme {
        SelectFavTeamDialog(openDialogState = true, updateDialogState = {}) {}
    }

}