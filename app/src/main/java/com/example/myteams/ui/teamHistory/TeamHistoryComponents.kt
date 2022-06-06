package com.example.myteams.ui.teamHistory

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myteams.R
import com.example.myteams.ui.theme.Typography

@Composable
fun MatchDate(
    modifier: Modifier = Modifier,
    matchDate: String?
) {

    val noMatchDate = stringResource(id = R.string.unknown_match_date)
    Text(
        modifier = modifier,
        text = matchDate ?: noMatchDate
    )

}

@Composable
fun Scored(modifier: Modifier = Modifier, score: String?) {

    val unknownScore = stringResource(id = R.string.unknown_score)
    Box(
        modifier = Modifier
            .wrapContentSize()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .padding(4.dp),

        contentAlignment = Alignment.Center
    ) {
        Text(text = score ?: unknownScore, fontSize = Typography.headlineLarge.fontSize)
    }

}


@Composable
fun TeamName(modifier: Modifier = Modifier, teamName:String?, alignment: Alignment = Alignment.Center) {
    val unknownScore = stringResource(id = R.string.unknown_score)
    Box(
        modifier = Modifier
            .wrapContentSize()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .padding(4.dp),

        contentAlignment = alignment
    ) {
        Text(text = teamName ?: unknownScore, fontSize = Typography.headlineSmall.fontSize)
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewMatchDate() {

    TeamName(teamName = "Arsenal")
}