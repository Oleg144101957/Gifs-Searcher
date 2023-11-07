package ua.searchgifs.apptech.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import ua.searchgifs.apptech.domain.repository.NetworkConnectionChecker

class NetworkConnectionCheckerImpl(private val context: Context) : NetworkConnectionChecker {

    override fun checkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
    }

}