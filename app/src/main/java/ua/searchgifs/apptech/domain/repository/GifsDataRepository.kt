package ua.searchgifs.apptech.domain.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ua.searchgifs.apptech.Constants
import ua.searchgifs.apptech.domain.model.DataResult


interface GifsDataRepository {

    @GET("gifs/trending?api_key=${Constants.DEV_KEY}")
    fun getGifsForTheGeneralScreen(): Call<DataResult>

    @GET("gifs/search?api_key=${Constants.DEV_KEY}")
    fun searchForGifs(@Query("q") query: String): Call<DataResult>

}