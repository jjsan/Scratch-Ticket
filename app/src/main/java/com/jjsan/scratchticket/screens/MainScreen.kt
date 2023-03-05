package com.jjsan.scratchticket.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.component.AppButton
import com.jjsan.scratchticket.component.ApplicationLabel
import com.jjsan.scratchticket.component.TicketStatus
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.ACTIVATION_SCREEN
import com.jjsan.scratchticket.navigation.NavigationRoutes.Companion.SCRATCH_SCREEN
import com.jjsan.scratchticket.viewmodel.TicketStatusViewModel


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

        Spacer(modifier = Modifier.height(50.dp))
        
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
