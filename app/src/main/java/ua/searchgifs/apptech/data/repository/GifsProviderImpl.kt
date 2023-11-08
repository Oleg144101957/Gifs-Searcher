package ua.searchgifs.apptech.data.repository

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.searchgifs.apptech.domain.model.DataObject
import ua.searchgifs.apptech.domain.model.DataResult
import ua.searchgifs.apptech.domain.repository.GifsDataRepository
import ua.searchgifs.apptech.domain.repository.GifsProvider
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GifsProviderImpl : GifsProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl(ua.searchgifs.apptech.Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val trendGifsRepo = retrofit.create(GifsDataRepository::class.java)



    override suspend fun provideGifsForTheGeneralScreen(): List<DataObject> = suspendCoroutine{ cont ->
        trendGifsRepo.getGifsForTheGeneralScreen().enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()

                if (body != null) {
                    cont.resume(body.res)
                }

            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                Log.d("123123", "onFailure in requestDataForGeneralScreen() method")
            }
        })
    }

    override suspend fun searchForGifs(searchWords: String): List<DataObject> = suspendCoroutine{ cont->

        trendGifsRepo.searchForGifs(searchWords).enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()

                if (body != null) {
                    cont.resume(body.res)
                }
            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                Log.d("123123", "onFailure in searchFotTheGifs() method")
            }
        })
    }
}