package com.example.calmly



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.calmly.navigation.CalmlyNavGraph
import com.example.calmly.ui.theme.CalmlyTheme
import com.example.calmly.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    )
    { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, proceed
        } else {
            Toast.makeText(this, "Notification permission is required", Toast.LENGTH_SHORT).show()
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //create notification chanel
        createNotificationChannel(this)

        askNotificationPermission()
        /*// Start Service from

        val intent = Intent(this, MusicService::class.java).apply {
            action = "PLAY"
        }

        ContextCompat.startForegroundService(this, intent)
*/

        enableEdgeToEdge()
        setContent {
            ViewModelProvider(this)[MainViewModel::class.java]
            CalmlyTheme {
                App()
            }
        }
    }

    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            "music_channel",
            "Music Controls",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(channel)
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}




@Composable
fun App() {
    CalmlyNavGraph()
}