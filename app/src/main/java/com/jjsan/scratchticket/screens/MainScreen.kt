package com.jjsan.scratchticket.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.component.AppButton
import com.jjsan.scratchticket.enums.TicketStatusEnum
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.ACTIVATION_SCREEN
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.SCRATCH_SCREEN
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel
import java.util.UUID


@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: TicketStatusViewModel
) {
    MainScreenWithViewModel(
        navHostController, viewModel
    )
}

@Composable
fun MainScreenWithViewModel(
    navHostController: NavHostController,
    ticketStatusViewModel: TicketStatusViewModel
) {
    Column {
        ApplicationLabel()

        val ticketStatusValues = ticketStatusViewModel.ticketStatus.collectAsState()
        val ticketStatus = ticketStatusValues.value.ticketStatus
        val ticketCode = ticketStatusValues.value.ticketCode

        TicketStatus(
            ticketStatus,
            ticketCode
        )

        AppButton(
            buttonLabel = stringResource(id = R.string.btn_scratch_ticket)
        ) {
            navHostController.navigate(SCRATCH_SCREEN)
        }

        Spacer(modifier = Modifier.height(50.dp))

        ticketCode?.run {
            AppButton(
                buttonLabel = stringResource(id = R.string.activate_ticket)
            ) {
                navHostController.navigate(ACTIVATION_SCREEN)
            }
        }
    }
}

@Composable
fun TicketStatus(
    ticketStatus: TicketStatusEnum,
    ticketCode: UUID?
) {
    Column() {
        Text(stringResource(R.string.ticket_status))
        Spacer(Modifier.height(50.dp))

        Text(getTicketStatus(ticketStatus = ticketStatus))

        ticketCode?.run {
            Text(
                stringResource(id = R.string.ticket_code) +
                        ticketCode
            )
        }
    }

}

@Composable
private fun getTicketStatus(ticketStatus: TicketStatusEnum): String {
    return when (ticketStatus) {
        TicketStatusEnum.NOT_SCRATCHED -> stringResource(id = R.string.not_scratched)
        TicketStatusEnum.SCRATCHED_NOT_ACTIVATED -> stringResource(id = R.string.scratched_not_activated)
        TicketStatusEnum.SCRATCHED_ACTIVATED -> stringResource(id = R.string.scratched_activated)
    }
}

@Composable
fun ApplicationLabel() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.bodyLarge
    )
}
