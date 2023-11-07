package ua.searchgifs.apptech.domain.model

sealed class Action(){

    data object RequestData : Action()
    data class SetDetailedGif(val url: String) : Action()
    data object GeneralScreen : Action()
    data class SearchGifs(val searchWords: String) : Action()

}
