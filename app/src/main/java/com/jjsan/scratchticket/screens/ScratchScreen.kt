package com.jjsan.scratchticket.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.component.AppButton
import com.jjsan.scratchticket.enums.TicketStatusEnum
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.MAIN_SCREEN
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel
import kotlinx.coroutines.Job
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
        val ticketStatus = ticketStatusViewModel.ticketStatus.collectAsState()

        Text(ticketStatus.value.ticketStatus.toString())
        Text(ticketStatus.value.ticketCode.toString())

        val scope = rememberCoroutineScope()
        val currentContext = LocalContext.current

        AppButton(
            buttonLabel = stringResource(id = R.string.btn_scratch_ticket)
        ) {
            job = scope.launch {
                try {
//                    delay(2000)
                    ticketStatusViewModel.setTicketStatus(TicketStatusEnum.SCRATCHED_NOT_ACTIVATED)
                    ticketStatusViewModel.setTicketCode(UUID.randomUUID())
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

        AppButton(
            buttonLabel = stringResource(id = R.string.cancel_scratch)
        ) {
            if (job.isActive) {
                job.cancel()
                ticketStatusViewModel.setTicketStatus(TicketStatusEnum.NOT_SCRATCHED)
                ticketStatusViewModel.setTicketCode(null)
            }
        }
    }

    BackHandler(enabled = true) {
        if (job.isActive) {
            job.cancel()
            ticketStatusViewModel.setTicketStatus(TicketStatusEnum.NOT_SCRATCHED)
            ticketStatusViewModel.setTicketCode(null)
        }
        navHostController.navigate(MAIN_SCREEN)
    }
}
