package ua.searchgifs.apptech.presantation

import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ua.searchgifs.apptech.domain.model.DataResult
import ua.searchgifs.apptech.domain.repository.GifsDataRepository
import ua.searchgifs.apptech.presantation.screens.MainNavigation
import ua.searchgifs.apptech.ui.theme.GifsSearcherTheme



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifsSearcherTheme {
                // A surface container using the 'background' color from the theme
                MainNavigation()
            }
        }
    }
}
