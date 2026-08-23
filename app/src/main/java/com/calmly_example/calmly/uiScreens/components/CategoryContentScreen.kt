package com.calmly_example.calmly.uiScreens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.calmly_example.calmly.data.Sound
import com.calmly_example.calmly.ui.theme.DesaturatedBlue
import com.calmly_example.calmly.ui.theme.SoftWhite
import com.calmly_example.calmly.viewmodel.MainViewModel

@Composable
fun CategoryContentScreen(
    viewModel: MainViewModel,
    sounds: List<Sound>,
    categoryName: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesaturatedBlue)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.headlineLarge,
                    color = SoftWhite
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                AutoSlidingImageRow(sounds)
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(24.dp))
            }

            soundCardList(
                sounds = sounds,
                viewModel = viewModel
            )
            
            item {
                Spacer(modifier = Modifier.height(100.dp)) // Extra space for bottom bar
            }
        }
    }
}
