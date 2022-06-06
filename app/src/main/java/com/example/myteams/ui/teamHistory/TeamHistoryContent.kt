package com.example.myteams.ui.teamHistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myteams.data.models.Result
import com.example.myteams.ui.favTeams.EmptySearchContent

@Composable
fun TeamHistoryContent(
    modifier: Modifier = Modifier,
    matches: List<Result>
) {

    if (matches.isEmpty()) {
        // todo need custom empty matches content
        EmptySearchContent()
    } else {
        LazyColumn {
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