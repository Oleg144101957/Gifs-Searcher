package ua.searchgifs.apptech.domain.model

sealed class MainState {

    object Loading : MainState()
    object NoInternetConnection : MainState()
    object DataForGeneralScreenIsReady : MainState()
    object DetailedScreen : MainState()

}