package com.jjsan.scratchticket.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.component.AppButton
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.MAIN_SCREEN
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID


@Composable
fun ScratchScreen(
    navHostController: NavHostController,
    viewModel: TicketStatusViewModel
) {
    ScratchScreenWithViewModel(navHostController, viewModel)
}

@Composable
fun ScratchScreenWithViewModel(
    navHostController: NavHostController,
    ticketStatusViewModel: TicketStatusViewModel
) {
    // do not remove explicit type
    var job: Job = Job()

    Column {
        val scope = rememberCoroutineScope()
        val currentContext = LocalContext.current
        var progress by remember { mutableStateOf("") }
        val progressScratching = stringResource(id = R.string.scratching)
        val progressScratched = stringResource(id = R.string.scratched)

        AppButton(
            buttonLabel = stringResource(id = R.string.btn_scratch_ticket)
        ) {
            job = scope.launch {
                try {
                    progress = progressScratching
                    delay(2000)
                    ticketStatusViewModel.setTicketScratched(UUID.randomUUID())
                    progress = progressScratched
                } catch (ex: Exception) {
                    Toast.makeText(currentContext, "Canceled", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        AppButton(
            buttonLabel = stringResource(id = R.string.back_to_main_screen)
        ) {
            navHostController.navigate(MAIN_SCREEN)
        }

        Spacer(modifier = Modifier.height(50.dp))

        val ticketStatusValues = ticketStatusViewModel.uiState.collectAsState()
        val ticketCode = ticketStatusValues.value.ticketCode

        Text(
            text = progress,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 20.dp)
        )

        ticketCode?.run {
            Text(
                text = ticketCode.toString(),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }

    BackHandler(enabled = true) {
        if (job.isActive) {
            job.cancel()
            ticketStatusViewModel.clearTicket()
        }
        navHostController.navigate(MAIN_SCREEN)
    }

}
