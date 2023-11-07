package ua.searchgifs.apptech.presantation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.searchgifs.apptech.presantation.viewmodel.MainViewModel


@Composable
fun MainNavigation(){

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()


    NavHost(navController = navController, startDestination = ScreensRoutes.LoadingScreen.route){

        composable(route = ScreensRoutes.LoadingScreen.route){
            LoadingScreen(navController, mainViewModel)
        }

        composable(route = ScreensRoutes.GeneralScreen.route){
            GeneralScreen(navController, mainViewModel)
        }

        composable(route = ScreensRoutes.DetailedScreen.route){
            DetailedScreen(navController, mainViewModel)
        }

        composable(route = ScreensRoutes.NoNetworkScreen.route){
            NoNetworkScreen(navController)
        }
    }
}