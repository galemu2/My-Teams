package com.example.myteams.ui.favTeams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myteams.R
import com.example.myteams.ui.theme.MediumGray

@Composable
fun EmptySearchContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.size(240.dp),
            painter = painterResource(id = R.drawable.ic_empty_list),
            contentDescription = stringResource(id = R.string.empty_team_list),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.no_teams),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}


@Preview
@Composable
fun EmptyFavesContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            painter = painterResource(id = R.drawable.ic_empty_favs_list),
            contentDescription = stringResource(id = R.string.empty_team_list),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.no_teams_in_list),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}

@Composable
fun ErrorContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier.size(240.dp),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.empty_team_list),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.error),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )

    }
}

