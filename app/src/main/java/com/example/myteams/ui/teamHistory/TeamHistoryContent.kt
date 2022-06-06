package com.example.myteams.ui.teamHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myteams.R
import com.example.myteams.data.models.Result
import com.example.myteams.ui.favTeams.EmptySearchContent
import com.example.myteams.ui.theme.contentBackground
import com.example.myteams.ui.theme.displayFavTeamBackground

@Composable
fun TeamHistoryContent(
    modifier: Modifier = Modifier,
    matches: List<Result>
) {

    if (matches.isEmpty()) {
        EmptySearchContent(text = stringResource(id = R.string.empty_team_list))
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = contentBackground)
        ) {
            items(items = matches, key = { match -> match.idEvent }) { match ->

                TeamMatch(match = match)
            }
        }
    }


}


@Composable
fun TeamMatch(
    modifier: Modifier = Modifier,
    match: Result
) {

    Column(
        modifier = Modifier
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(color = displayFavTeamBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MatchDate(matchDate = match.dateEvent)
        Row {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TeamName(teamName = match.strHomeTeam)
                Scored(score = match.intHomeScore)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TeamName(teamName = match.strAwayTeam)

                Scored(score = match.intAwayScore)
            }
        }
    }


}