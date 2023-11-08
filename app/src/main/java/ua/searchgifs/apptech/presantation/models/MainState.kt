package ua.searchgifs.apptech.presantation.models

sealed class MainState {

    object Loading : MainState()
    object NoInternetConnection : MainState()
    object DataForGeneralScreenIsReady : MainState()
    object DetailedScreen : MainState()

}