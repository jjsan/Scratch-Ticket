package com.jjsan.scratchticket.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jjsan.scratchticket.R

@Composable
fun ApplicationLabel() {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
    }
}