package com.example.myteams.ui.favTeams

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myteams.R
import com.example.myteams.data.models.Team
import com.example.myteams.data.models.Teams
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.theme.MyTeamsTheme
import com.example.myteams.ui.theme.basicTextColor
import com.example.myteams.ui.theme.displayFavTeamBackground
import com.example.myteams.ui.theme.teamNameColor
import com.example.myteams.util.Resource


@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun HandleTeamContent(
    viewModel: FavTeamsViewModel,
) {

    var teamSearch by remember {
        mutableStateOf(viewModel.searchTeam)
    }


    LaunchedEffect(key1 = viewModel.searchTeam, block = {
        teamSearch = viewModel.searchTeam
    })

    if (teamSearch.value is Resource.Loading) {
        LoadingContent()
    } else if (teamSearch.value is Resource.Error) {
        Text(text = "Error", fontSize = 40.sp)
    } else if (teamSearch.value is Resource.Success) {
        DisplayFavTeams(teams = (teamSearch.value as Resource.Success<Teams>).data!!.teams)
    } else {
        EmptyContent()
    }
}


@Composable
fun DisplayFavTeams(teams: List<Team>) {

    LazyColumn(
        modifier = Modifier,
    ) {
        items(
            items = teams, // Todo update the list of items

        ) { team ->

            TeamItem(team = team)

        }
    }
}

@Composable
fun TeamItem(modifier: Modifier = Modifier, team: Team) {

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
    ) {

        TeamBadge(
            modifier = Modifier.weight(1f),
            badge = team.strTeamBadge
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
                sportName = team.strSport
            )
            LeagueName(
                modifier = Modifier.weight(1f),
                leagueName = team.strLeague
            )
        }

        TeamJersey(
            modifier = Modifier.weight(1f),
            teamJersey = team.strTeamJersey
        )
    }
}

@Composable
fun TeamJersey(modifier: Modifier = Modifier, teamJersey: String = "") {

    val context = LocalContext.current

    val placeHolder: Painter = painterResource(id = R.drawable.placeholder_jersey)
    val description = stringResource(id = R.string.team_jersey)
    // todo stateless component that takes in string to load image
    val model = ImageRequest
        .Builder(context)
        .data(teamJersey)
        .crossfade(true)
        .build()




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
            error = placeHolder,
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun TeamBadge(modifier: Modifier = Modifier, badge: String = "") {

    val context = LocalContext.current

    // todo stateless componenet that takes in string to load image
    val model = ImageRequest
        .Builder(context)
        .data(badge)
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
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .padding(4.dp),
        text = name,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
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
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        text = sportName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,
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
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp),
        text = leagueName,
        fontFamily = FontFamily.SansSerif,
        maxLines = 1,
        fontSize = 10.sp,
        overflow = TextOverflow.Ellipsis,
        color = basicTextColor,


        )

}

/*
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
        SportName()
    }
}
 */
