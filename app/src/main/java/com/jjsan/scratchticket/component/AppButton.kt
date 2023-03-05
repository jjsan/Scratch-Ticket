package com.jjsan.scratchticket.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    buttonLabel: String,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .background(color = Color.DarkGray)
            .padding(10.dp)
            .clickable { onClick() }
            .wrapContentSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = buttonLabel,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}
