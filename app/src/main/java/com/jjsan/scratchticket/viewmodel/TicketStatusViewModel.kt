package com.jjsan.scratchticket.viewmodel

import androidx.lifecycle.ViewModel
import com.jjsan.scratchticket.enums.TicketStatusEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID


data class TicketStatusData(
    var ticketStatus: TicketStatusEnum = TicketStatusEnum.NOT_SCRATCHED,
    var ticketCode: UUID? = null
)

class TicketStatusViewModel : ViewModel() {

    private val _ticketStatus = MutableStateFlow(TicketStatusData())
    val uiState = _ticketStatus.asStateFlow()


    fun setTicketScratched(ticketCode: UUID?) {
        setTicketStatus(TicketStatusEnum.SCRATCHED_NOT_ACTIVATED)
        setTicketCode(ticketCode)
    }

    fun setTicketActivated() {
        setTicketStatus(TicketStatusEnum.SCRATCHED_ACTIVATED)
    }

    fun clearTicket() {
        setTicketStatus(TicketStatusEnum.NOT_SCRATCHED)
        setTicketCode(null)
    }

    private fun setTicketStatus(ticketStatus: TicketStatusEnum) {
        this._ticketStatus.update { currentState ->
            currentState.copy(
                ticketStatus = ticketStatus
            )
        }
    }

    private fun setTicketCode(ticketCode: UUID?) {
        _ticketStatus.update { currentState ->
            currentState.copy(
                ticketCode = ticketCode
            )
        }
    }

 }
