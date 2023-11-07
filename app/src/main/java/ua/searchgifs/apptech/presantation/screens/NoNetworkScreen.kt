package ua.searchgifs.apptech.presantation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ua.searchgifs.apptech.R


@Composable
fun NoNetworkScreen(navController: NavHostController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anikihamster))

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
                text = "No Internet Connection (",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Refresh Screen",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(96.dp)
                .clickable {
                navController.navigate(ScreensRoutes.LoadingScreen.route)
            })

    }
}