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

class TicketStatusViewModel() : ViewModel() {

    private val _ticketStatus = MutableStateFlow(TicketStatusData())
    val ticketStatus = _ticketStatus.asStateFlow()

    fun setTicketStatus(ticketStatus: TicketStatusEnum) {
        this._ticketStatus.update { currentState ->
            currentState.copy(
                ticketStatus = ticketStatus
            )
        }
    }

    fun setTicketCode(ticketCode: UUID?) {
        _ticketStatus.update { currentState ->
            currentState.copy(
                ticketCode = ticketCode
            )
        }
    }

 }
