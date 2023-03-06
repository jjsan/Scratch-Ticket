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

    val ticketStatusValues = ticketStatusViewModel.uiState.collectAsState()
    val ticketCode = ticketStatusValues.value.ticketCode
    val ticketStatus = ticketStatusValues.value.ticketStatus

    var ticketActivated by remember { mutableStateOf(false) }
    var showActivationError by remember { mutableStateOf(false) }

    Column {
        AppButton(buttonLabel = stringResource(id = R.string.activate_ticket)) {
            scope.launch {
                ticketCode?.let {
                    showActivationError = false
                    ticketActivated = TicketActivation().activateTicket(it)

                    if (ticketActivated) {
                        ticketStatusViewModel.setTicketActivated()
                    } else {
                        showActivationError = true
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = if (ticketActivated) {
                stringResource(R.string.activation_successful)
            } else {
                if (ticketStatus == TicketStatusEnum.SCRATCHED_ACTIVATED) {
                    stringResource(R.string.already_activated)
                } else {
                    ""
                }
            }
        )

        if (showActivationError) {
            Text(
                text = stringResource(id = R.string.error_activation)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        AppButton(
            buttonLabel = stringResource(id = R.string.back_to_main_screen)
        ) {
            navHostController.navigate(MAIN_SCREEN)
        }

        if (showActivationError) {
            showActivationError = true
            ShowSimpleNotification(
                stringResource(id = R.string.app_name),
                stringResource(id = R.string.error_activation)
            )
        }
    }
}
