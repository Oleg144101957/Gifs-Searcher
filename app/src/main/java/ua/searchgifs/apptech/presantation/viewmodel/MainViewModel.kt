package ua.searchgifs.apptech.presantation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.searchgifs.apptech.domain.model.Action
import ua.searchgifs.apptech.domain.model.DataObject
import ua.searchgifs.apptech.domain.model.DataResult
import ua.searchgifs.apptech.domain.model.MainState
import ua.searchgifs.apptech.domain.repository.GifsDataRepository
import ua.searchgifs.apptech.domain.repository.NetworkConnectionChecker
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val networkConnectionChecker: NetworkConnectionChecker) :
    ViewModel() {

    private val _liveState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val liveState: StateFlow<MainState> = _liveState

    private val _liveTrends: MutableStateFlow<List<DataObject>> = MutableStateFlow(listOf())
    val liveTrends: StateFlow<List<DataObject>> = _liveTrends

    private val _oneGif: MutableStateFlow<String> = MutableStateFlow("")
    val oneGif: StateFlow<String> = _oneGif


    private val retrofit = Retrofit.Builder()
        .baseUrl(ua.searchgifs.apptech.Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val trendGifsRepo = retrofit.create(GifsDataRepository::class.java)

    fun postData(action: Action) {

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
        val isConnected = networkConnectionChecker.checkConnection()
        if (isConnected) {
            //retro

            trendGifsRepo.getGifsForTheGeneralScreen().enqueue(object : Callback<DataResult?> {
                override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                    val body = response.body()

                    if (body != null) {
                        _liveTrends.value = body.res
                        _liveState.value = MainState.DataForGeneralScreenIsReady
                    }

                    Log.d("123123", "THE BODY IS $body")
                }

                override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                    Log.d("123123", "onFailure in requestDataForGeneralScreen() method")
                }
            })

        } else {
            //no network
            _liveState.value = MainState.NoInternetConnection
        }
    }

    private fun searchForTheGifs(searchWords: String) {
        val isConnected = networkConnectionChecker.checkConnection()
        if (isConnected) {
            //retro

            Log.d("123123", "searchForTheGifs() method")

            trendGifsRepo.searchForGifs(searchWords).enqueue(object : Callback<DataResult?> {
                override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                    val body = response.body()

                    if (body != null) {
                        _liveTrends.value = body.res
                    }

                    Log.d("123123", "THE BODY IS $body")
                }

                override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                    Log.d("123123", "onFailure in searchFotTheGifs() method")
                }
            })

        } else {
            //no network
            _liveState.value = MainState.NoInternetConnection
        }
    }
}