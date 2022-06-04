package com.example.myteams.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myteams.navigation.SetUpNavigation
import com.example.myteams.ui.theme.MyTeamsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<FavTeamsViewModel>()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MyTeamsTheme {
                navController = rememberNavController()// rememberAnimatedNavController()

//                FavTeamScreen()
                SetUpNavigation(
                    navController = navController,
                    viewModel = viewModel
                )
            }


        }
    }
}
