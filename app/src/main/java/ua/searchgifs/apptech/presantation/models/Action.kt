package ua.searchgifs.apptech.presantation.models

sealed class Action(){

    data object RequestData : Action()
    data class SetDetailedGif(val url: String) : Action()
    data object GeneralScreen : Action()
    data class SearchGifs(val searchWords: String) : Action()

}
