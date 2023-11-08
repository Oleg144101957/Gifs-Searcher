package ua.searchgifs.apptech.presantation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ua.searchgifs.apptech.R
import ua.searchgifs.apptech.presantation.models.Action
import ua.searchgifs.apptech.presantation.models.MainState
import ua.searchgifs.apptech.presantation.viewmodel.MainViewModel
import ua.searchgifs.apptech.ui.theme.GeneralScreenTypography

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GeneralScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val mainState = mainViewModel.liveState.collectAsState()
    val searchText = remember { mutableStateOf("") }

    LaunchedEffect(searchText.value){
        if (searchText.value.length > 2){
            mainViewModel.submitAction(Action.SearchGifs(searchText.value))
        }
    }

    when (mainState.value) {
        is MainState.Loading -> {}
        is MainState.DataForGeneralScreenIsReady -> {}
        is MainState.NoInternetConnection -> {
            navController.navigate(ScreensRoutes.NoNetworkScreen.route)
        }
        is MainState.DetailedScreen -> {
            navController.navigate(ScreensRoutes.DetailedScreen.route)
        }
    }


    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "general screen background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "General screen",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = GeneralScreenTypography.bodyLarge
        )

        TextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = { Text(text = "Search something...") }
        )

        GifsList(mainViewModel)

    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ColumnScope.GifsList(mainViewModel: MainViewModel){

    val padding = dimensionResource(id = R.dimen.spacing_small)
    val listOfDataWithGifs = mainViewModel.liveTrends.collectAsState()

    LazyColumn(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        items(listOfDataWithGifs.value.size) {
            GlideImage(model = listOfDataWithGifs.value[it].images.originalImage.url,
                contentDescription = "Images",
                modifier = Modifier
                    .padding(padding)
                    .clickable {
                        mainViewModel.submitAction(
                            Action.SetDetailedGif(listOfDataWithGifs.value[it].images.originalImage.url)
                        )
                    }
            )
        }
    }
}

