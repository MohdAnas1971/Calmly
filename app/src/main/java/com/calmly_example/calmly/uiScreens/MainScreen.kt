package com.calmly_example.calmly.uiScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.calmly_example.calmly.R
import com.calmly_example.calmly.ui.theme.CloudWhite
import com.calmly_example.calmly.ui.theme.DesaturatedBlue
import com.calmly_example.calmly.uiScreens.components.soundCardList
import com.calmly_example.calmly.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val selectedTab = remember { mutableIntStateOf(0) }
    val tabs = listOf("Main", "Meditation", "Sleep")

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = CloudWhite,
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
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab.intValue) {
                0 -> ContentLazyList(viewModel)
                1 -> MeditationScreen(viewModel)
                2 -> SleepScreen(viewModel)
            }
        }
    }
}

@Composable
fun ContentLazyList(viewModel: MainViewModel) {
    val verticalBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color(0x5B6E7D91),
            DesaturatedBlue
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.backgorund_5),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                ) {
                    Text(
                        text = "Calmly",
                        color = Color.White,
                        style = MaterialTheme.typography.displayLarge,
                        fontFamily = FontFamily.Cursive
                    )
                }
            }

            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(verticalBrush)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = DesaturatedBlue)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Recommended for You",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        item { CategoryCard(R.drawable.maditation_thumbnile, "Meditation") }
                        item { CategoryCard(R.drawable.sleeping_thumbline1, "Sleep") }
                    }
                    Text(
                        text = "Popular on Calmly",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }

            // Using the LazyListScope extension here
            soundCardList(
                sounds = viewModel.popularOnCalmly,
                viewModel = viewModel
            )
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




