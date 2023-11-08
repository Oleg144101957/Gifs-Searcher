package ua.searchgifs.apptech.presantation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ua.searchgifs.apptech.R
import ua.searchgifs.apptech.presantation.models.Action
import ua.searchgifs.apptech.presantation.viewmodel.MainViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailedScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val url = mainViewModel.oneGif.collectAsState()
    val padding = dimensionResource(id = R.dimen.spacing_small)

    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "detailed screen background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()) {
        GlideImage(
            model = url.value,
            contentDescription = "Detailed Gif",
            modifier = Modifier
                .align(Alignment.Center)
        )

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Button back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(padding)
                .clickable {
                    mainViewModel.submitAction(Action.GeneralScreen)
                    navController.navigate(ScreensRoutes.GeneralScreen.route)
                }
        )
    }
}