package ua.searchgifs.apptech.presantation.screens

sealed class ScreensRoutes(val route: String){

    object LoadingScreen : ScreensRoutes("loading")
    object GeneralScreen : ScreensRoutes("general_screen")
    object DetailedScreen : ScreensRoutes("detailed_screen")
    object NoNetworkScreen : ScreensRoutes("no_network_screen")

}