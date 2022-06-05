package com.example.myteams.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val MediumGray = Color(0xFF9C9C9C)


val teamNameColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.DarkGray // todo

val basicTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val displayFavTeamBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.DarkGray else PurpleGrey80


val topAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else LightGray


val topAppBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else PurpleGrey40