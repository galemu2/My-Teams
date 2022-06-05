package com.example.myteams.ui.favTeams

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.myteams.R
import com.example.myteams.ui.FavTeamsViewModel
import com.example.myteams.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.myteams.ui.theme.topAppBarBackgroundColor
import com.example.myteams.ui.theme.topAppBarContentColor
import com.example.myteams.util.Resource


@Composable
fun TeamsAppBar(
    viewModel: FavTeamsViewModel,
    searchAppBarOpenedState: Boolean,
    searchTextState: String
) {


    if (searchAppBarOpenedState) {
        SearchTeamAppBar(
            text = searchTextState,
            onTextChanged = { newText ->
                viewModel.searchTextState.value = newText
            },
            onCloseClicked = {
                viewModel.searchAppBarOpenState.value = false
                viewModel.searchTextState.value = ""
                viewModel.searchTeam.value = Resource.Loading() // todo show saved teams
            },
            onSearchClicked = { searchQuery ->
                viewModel.searchFavTeam(searchQuery = searchQuery)
            }
        )
    } else {
        DefaultTeamsAppBar {
            viewModel.searchAppBarOpenState.value = true
        }
    }
}

@Composable
fun DefaultTeamsAppBar(
    onSearchClicked: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.my_teams),
                color = topAppBarContentColor
            )
        },
        actions = {
            SearchAction(onSearchClicked = onSearchClicked)
        },
        backgroundColor = topAppBarBackgroundColor
    )
}


@Composable
fun SearchTeamAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = topAppBarBackgroundColor
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.search_teams),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.alpha(ContentAlpha.disabled)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_teams),
                        tint = topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChanged("")
                    }
                    onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.search_teams),
                        tint = topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)

                },

                ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {

    IconButton(onClick = {
        onSearchClicked()
    }) {

        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_teams),
            tint = topAppBarContentColor
        )
    }
}

@Preview
@Composable
fun PreviewSearchTeamAppBar() {
    SearchTeamAppBar(
        text = "",
        onTextChanged = {},
        onCloseClicked = { },
        onSearchClicked = {}
    )
}