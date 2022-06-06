package com.example.myteams.ui.favTeams

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myteams.R
import com.example.myteams.ui.theme.basicTextColor
import com.example.myteams.ui.theme.teamNameColor

@Composable
fun TeamJersey(modifier: Modifier = Modifier, teamJersey: String = "") {

    val context = LocalContext.current

    val placeHolder: Painter = painterResource(id = R.drawable.placeholder_jersey)
    val description = stringResource(id = R.string.team_jersey)
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
                color = Color.Transparent,
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

    val model = ImageRequest
        .Builder(context)
        .data(badge)
        .crossfade(true)
        .build()


    val placeHolder = painterResource(id = R.drawable.placeholder_badge)
    val description = stringResource(id = R.string.team_badge)


    Box(
        modifier = modifier
            .size(100.dp)
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
        modifier = Modifier
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


