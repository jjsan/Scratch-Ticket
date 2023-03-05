package com.jjsan.scratchticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjsan.scratchticket.navigation.ApplicationNavigation
import com.jjsan.scratchticket.ui.theme.ScratchTicketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScratchTicketTheme {
                // A surface container using the 'background' color from the theme
                ApplicationNavigation()
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScratchTicketTheme {
        Greeting("Android")
    }
}*/
