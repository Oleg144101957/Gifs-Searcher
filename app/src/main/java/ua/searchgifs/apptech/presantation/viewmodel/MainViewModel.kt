package ua.searchgifs.apptech.presantation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.searchgifs.apptech.domain.model.DataObject
import ua.searchgifs.apptech.domain.model.DataResult
import ua.searchgifs.apptech.domain.repository.GifsDataRepository
import ua.searchgifs.apptech.domain.repository.GifsProvider
import ua.searchgifs.apptech.domain.repository.NetworkConnectionChecker
import ua.searchgifs.apptech.presantation.models.Action
import ua.searchgifs.apptech.presantation.models.MainState
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkConnectionChecker: NetworkConnectionChecker,
    private val gifsProvider: GifsProvider
) : ViewModel() {

    private val _liveState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val liveState: StateFlow<MainState> = _liveState

    private val _liveTrends: MutableStateFlow<List<DataObject>> = MutableStateFlow(listOf())
    val liveTrends: StateFlow<List<DataObject>> = _liveTrends

    private val _oneGif: MutableStateFlow<String> = MutableStateFlow("")
    val oneGif: StateFlow<String> = _oneGif

    fun submitAction(action: Action) {

        when (action) {

            is Action.RequestData -> {
                requestDataForGeneralScreen()
            }

            is Action.SearchGifs -> {
                searchForTheGifs(action.searchWords)
            }

            is Action.SetDetailedGif -> {
                setOneGifUrl(action.url)
            }

            is Action.GeneralScreen -> {
                setGeneralScreenState()
            }
        }
    }

    private fun setOneGifUrl(urlForTheDetailedScreen: String) {
        _oneGif.value = urlForTheDetailedScreen
        _liveState.value = MainState.DetailedScreen
    }

    private fun setGeneralScreenState() {
        _liveState.value = MainState.DataForGeneralScreenIsReady
    }


    private fun requestDataForGeneralScreen() {

        viewModelScope.launch {

            val isConnected = networkConnectionChecker.checkConnection()

            if (isConnected) {
                //retro
                val gifsForTheGeneralScreen = gifsProvider.provideGifsForTheGeneralScreen()
                _liveTrends.value = gifsForTheGeneralScreen
                _liveState.value = MainState.DataForGeneralScreenIsReady

            } else {
                //no network
                _liveState.value = MainState.NoInternetConnection
            }
        }
    }

    private fun searchForTheGifs(searchWords: String) {
        viewModelScope.launch {
            val isConnected = networkConnectionChecker.checkConnection()
            if (isConnected) {
                //retro
                val searchResult = gifsProvider.searchForGifs(searchWords)
                _liveTrends.value = searchResult

            } else {
                //no network
                _liveState.value = MainState.NoInternetConnection
            }
        }
    }
}