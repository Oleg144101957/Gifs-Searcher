package ua.searchgifs.apptech.presantation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import ua.searchgifs.apptech.R
import ua.searchgifs.apptech.domain.model.Action
import ua.searchgifs.apptech.domain.model.MainState
import ua.searchgifs.apptech.presantation.viewmodel.MainViewModel

@Composable
fun LoadingScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val mainFont = FontFamily(Font(R.font.ostrovsky))

    val mainState = mainViewModel.liveState.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loadinganim))

    when (mainState.value) {
        is MainState.Loading -> {  }

        is MainState.NoInternetConnection -> {
            navController.navigate(ScreensRoutes.NoNetworkScreen.route)
        }

        is MainState.DataForGeneralScreenIsReady -> {
            navController.navigate(ScreensRoutes.GeneralScreen.route)
        }

        is MainState.DetailedScreen -> {
            navController.navigate(ScreensRoutes.DetailedScreen.route)
        }
    }


    LaunchedEffect("loading") {
        delay(1000)
        mainViewModel.postData(Action.RequestData)
    }


    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "start screen background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )


    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.align(Alignment.Center)) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Loading...",
                color = Color.White,
                fontFamily = mainFont,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}