package com.example.myteams.ui.favTeams

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myteams.BuildConfig
import com.example.myteams.R
import com.example.myteams.ui.theme.MyTeamsTheme
import com.example.myteams.ui.theme.basicTextColor
import com.example.myteams.ui.theme.teamNameColor


@Composable
fun HandleTeamContent(
    teamitesm: List<Int> = emptyList()
) {

    if (teamitesm.isEmpty()) {
        // todo display empty screen
    } else {
        DisplayTeams()
    }
}


@Composable
fun DisplayTeams(teamitesm: List<Int> = emptyList()) {

    LazyColumn {
        items(
            items = listOf<Int>(1, 2, 3), // Todo update the list of items

        ) { item ->

            TeamItem()

        }
    }
}

@Composable
fun TeamItem(modifier: Modifier = Modifier) {


    //             .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)
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
            .background(color = Color.Transparent)
            .height(100.dp)
    ) {

        TeamBadge(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.weight(4f),
        ) {
            TeamName(modifier = Modifier.weight(2f))
            SportName(modifier = Modifier.weight(1f))
            LeagueName(modifier = Modifier.weight(1f))
        }

        TeamJersey(modifier = Modifier.weight(1f))
    }
}

@Composable
fun TeamJersey(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    // todo stateless component that takes in string to load image
    val model = ImageRequest
        .Builder(context)
        .data("https://www.thesportsdb.com/images/media/team/jersey/0006oc1626543801.png/preview")
        .crossfade(true)
        .build()

    val placeHolder: Painter = painterResource(id = R.drawable.placeholder_jersey)
    val description = stringResource(id = R.string.team_jersey)


    Box(
        modifier = modifier
            .size(100.dp)
            .background(color = Color.Transparent)
            .clip(
                RoundedCornerShape(2.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Transparent, // todo border color update
                shape = RoundedCornerShape(1.dp)
            ), contentAlignment = Alignment.Center

    ) {

        AsyncImage(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    color = Color.Transparent
                ),
            model = model,
            contentDescription = description,
            placeholder = placeHolder,
            fallback = placeHolder,
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun TeamBadge(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    // todo stateless componenet that takes in string to load image
    val model = ImageRequest
        .Builder(context)
        .data("https://www.thesportsdb.com/images/media/team/badge/uyhbfe1612467038.png/preview")
        .crossfade(true)
        .build()


    val placeHolder = painterResource(id = R.drawable.placeholder_badge)
    val description = stringResource(id = R.string.team_badge)


    Box(
        modifier = modifier
            .size(100.dp) // todo size may be updated
            .clip(shape = CircleShape)
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = Color.Transparent
                ),
            model = model,
            contentDescription = description,
            placeholder = placeHolder,
            fallback = placeHolder,
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun TeamName(
    modifier: Modifier = Modifier,
    name: String = stringResource(id = R.string.team_name)
) {

    Text(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),
        text = name,
        fontSize = 30.sp, fontFamily = FontFamily.Serif,
        color = teamNameColor
    )
}

@Composable
fun SportName(
    modifier: Modifier = Modifier,
    sportName: String = stringResource(id = R.string.sport_name)
) {

    Text(
        modifier = modifier
            .padding(4.dp)
            .wrapContentSize(),
        text = sportName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        color = basicTextColor
    )
}

@Composable
fun LeagueName(
    modifier: Modifier = Modifier,
    leagueName: String = stringResource(id = R.string.league_name)
) {
    Text(
        modifier = modifier
            .padding(4.dp)
            .wrapContentSize(),
        text = leagueName,
        fontFamily = FontFamily.SansSerif,
        maxLines = 1,
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        color = basicTextColor

    )

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
fun PreviewTeamBadge() {
    MyTeamsTheme {
        DisplayTeams()
    }
}
 