package com.jjsan.scratchticket

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jjsan.scratchticket.MainActivity.Companion.CHANNEL_NAME
import com.jjsan.scratchticket.MainActivity.Companion.NOTIFICATION_ID
import com.jjsan.scratchticket.navigation.ApplicationNavigation
import com.jjsan.scratchticket.ui.theme.ScratchTicketTheme
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        const val CHANNEL_NAME = "MyTestChannel"
        const val NOTIFICATION_ID = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: TicketStatusViewModel by viewModels()

        setContent {
            ScratchTicketTheme {
                // A surface container using the 'background' color from the theme
                NotificationApp()
                ApplicationNavigation(viewModel)
            }
        }
    }
}

@Composable
fun NotificationApp() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        createNotificationChannel(context)
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val descriptionText = "My important test channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_NAME, CHANNEL_NAME, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

// shows notification with a title and one-line content text
@Composable
fun ShowSimpleNotification(
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val context = LocalContext.current
    val builder = NotificationCompat.Builder(context, CHANNEL_NAME)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)


    if (ActivityCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            CheckPostNotificationPermissions()
        }
    } else {
        NotificationManagerCompat.from(context).notify(
            NOTIFICATION_ID,
            builder.build()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CheckPostNotificationPermissions() {
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _: Boolean ->
    }
    scope.launch {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}
