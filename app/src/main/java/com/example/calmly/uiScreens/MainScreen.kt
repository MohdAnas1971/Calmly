package com.example.calmly.uiScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calmly.R
import com.example.calmly.ui.theme.CloudWhite
import com.example.calmly.ui.theme.DesaturatedBlue
import com.example.calmly.uiScreens.components.SoundCardList
import com.example.calmly.viewmodel.MainViewModel


@Composable
 fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val selectedTab = remember { mutableIntStateOf(0) }

    val tabs = listOf("Main", "Meditation", "Sleep")
    Scaffold(

        bottomBar ={

            BottomAppBar(
                containerColor =CloudWhite,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                TabRow(
                    selectedTabIndex = selectedTab.intValue,
                    containerColor = CloudWhite,
                    ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab.intValue == index,
                            onClick = { selectedTab.intValue = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
    },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

            when (selectedTab.intValue) {
                0 -> ContentLazyList(innerPadding,viewModel)
                1 -> MeditationScreen(viewModel)
                2 -> SleepScreen(viewModel)
            }
    }
}


@Composable
fun ContentLazyList(innerPadding: PaddingValues, viewModel: MainViewModel) {

    val horizontalBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color(0x5B6E7D91),
            DesaturatedBlue
        )
    )
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Image(
            painter = painterResource(R.drawable.backgorund5),
            contentScale = ContentScale.Crop,
            contentDescription = "main screen background Image",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                "Calmly",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.displayLarge, fontFamily = FontFamily.Cursive,
                //TextStyle(fontFamily =FontFamily.Cursive, fontWeight = FontWeight.Bold, fontSize =48.sp ),
            )
        }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(horizontalBrush)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6000.dp)
                    .background(color = DesaturatedBlue)
                    .padding(horizontal = 8.dp)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        "Recommended for You",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    LazyRow(contentPadding = PaddingValues(top = 10.dp)) {
                        item {
                            CategoryCard(R.drawable.maditation_thumbnile, "Meditation")
                            Spacer(Modifier.width(15.dp))
                        }
                        item {
                            CategoryCard(R.drawable.sleeping_thumbline1, "Sleeping")
                            Spacer(Modifier.width(15.dp))
                        }
                    }
                    Text(
                        "Popular on Calmly",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    SoundCardList(viewModel=viewModel, sounds = viewModel.popularOnCalmly)
                }
            }
        }
    }
}
}


@Composable
fun CategoryCard(imResId: Int, name:String) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp, pressedElevation =3.dp, focusedElevation = 5.dp),
        modifier = Modifier
            .size(300.dp, 200.dp)) {
        Image(
            painter = painterResource(imResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(name, style = MaterialTheme.typography.bodyLarge)
    }

}



