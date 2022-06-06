package com.example.myteams.ui.teamHistory

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.myteams.R
import com.example.myteams.ui.theme.topAppBarContentColor


@Composable
fun TeamHistoryAppBar(
    onNavigteBack: () -> Unit
) {

    val text = stringResource(
        id = R.string.team_history
    )

    val back = stringResource(id = R.string.back)
    TopAppBar(modifier = Modifier,
        title = {
            Text(
                text = text,
                color = topAppBarContentColor
            )
        },
        actions = {},
        navigationIcon = {
            IconButton(onClick = { onNavigteBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = back
                )
            }
        })
}