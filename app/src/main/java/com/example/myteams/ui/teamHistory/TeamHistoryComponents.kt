package com.example.myteams.ui.teamHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myteams.R
import com.example.myteams.ui.theme.Typography
import com.example.myteams.ui.theme.matchNameColor
import com.example.myteams.ui.theme.scoreBoradBackgound

@Composable
fun MatchDate(
    modifier: Modifier = Modifier,
    matchDate: String?
) {

    val noMatchDate = stringResource(id = R.string.unknown_match_date)
    Text(
        modifier = modifier,
        text = matchDate ?: noMatchDate,
        color = matchNameColor,
        maxLines = 1
    )

}

@Composable
fun Scored(modifier: Modifier = Modifier, score: String?) {

    val unknownScore = stringResource(id = R.string.unknown_score)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(100.dp)
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
        .background(color = scoreBoradBackgound),

        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = score ?: unknownScore,
            fontSize = Typography.headlineLarge.fontSize,
            maxLines = 1,
            color = matchNameColor
        )
    }

}


@Composable
fun TeamName(
    modifier: Modifier = Modifier,
    teamName: String?,
    alignment: Alignment = Alignment.Center
) {
    val unknownScore = stringResource(id = R.string.unknown_score)

    val team = teamName?.split(" ")
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),

        contentAlignment = alignment
    ) {
        Text(
            text = team?.last() ?: unknownScore,
            fontSize = Typography.bodyLarge.fontSize,
            maxLines = 1,
            color = matchNameColor,
            fontWeight = FontWeight.Bold
        )
    }

}

