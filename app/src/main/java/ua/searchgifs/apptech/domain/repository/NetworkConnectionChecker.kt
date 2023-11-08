package ua.searchgifs.apptech.domain.repository

interface NetworkConnectionChecker {
    fun checkConnection() : Boolean

}