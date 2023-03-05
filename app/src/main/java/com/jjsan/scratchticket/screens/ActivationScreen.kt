package com.jjsan.scratchticket.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.ShowSimpleNotification
import com.jjsan.scratchticket.api.TicketActivation
import com.jjsan.scratchticket.component.AppButton
import com.jjsan.scratchticket.enums.TicketStatusEnum
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.MAIN_SCREEN
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel
import kotlinx.coroutines.launch

@Composable
fun ActivationScreen(
    navHostController: NavHostController,
    ticketStatusViewModel: TicketStatusViewModel
) {
    val scope = rememberCoroutineScope()

    val ticketStatusValues = ticketStatusViewModel.ticketStatus.collectAsState()
    val ticketCode = ticketStatusValues.value.ticketCode

    var ticketActivated by remember { mutableStateOf(false) }
    var showActicationError by remember { mutableStateOf(false) }

    Column {
        AppButton(buttonLabel = stringResource(id = R.string.activate_ticket)) {
            scope.launch {
                ticketCode?.let {
                    ticketActivated = TicketActivation().activateTicket(it)

                    ticketStatusViewModel.setTicketStatus(TicketStatusEnum.SCRATCHED_ACTIVATED)

                    if (!ticketActivated) {
                        showActicationError = true
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = if (ticketActivated) {
                stringResource(R.string.activation_succesfull)
            } else {
                stringResource(R.string.activation_unseccesful)
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        ticketCode?.run {
            AppButton(
                buttonLabel = stringResource(id = R.string.back_to_main_screen)
            ) {
                navHostController.navigate(MAIN_SCREEN)
            }
        }

        if (showActicationError) {
            ShowSimpleNotification(
                "Simple notification",
                "This is a simple notification with default priority."
            )
        }
    }
}
