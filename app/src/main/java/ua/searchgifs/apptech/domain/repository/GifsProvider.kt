package ua.searchgifs.apptech.domain.repository

import ua.searchgifs.apptech.domain.model.DataObject

interface GifsProvider {

    suspend fun provideGifsForTheGeneralScreen(): List<DataObject>

    suspend fun searchForGifs(searchWords: String): List<DataObject>

}