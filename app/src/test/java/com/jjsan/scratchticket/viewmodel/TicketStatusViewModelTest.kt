package com.jjsan.scratchticket.viewmodel

import com.jjsan.scratchticket.enums.TicketStatusEnum
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import java.util.UUID


class TicketStatusViewModelTest {

    private val ticketStatusViewModel = TicketStatusViewModel()

    private var currentTicketStatusUiState = TicketStatusData()
    private var ticketStatus = TicketStatusEnum.NOT_SCRATCHED
    private var tickedCode: UUID? = null

    @Test
    fun newCreatedTicketIsNotScratchedAndCodeIsNullOnNewTicket() {
        createNewTicket()

        updateValues()

        assertEquals(ticketStatus, TicketStatusEnum.NOT_SCRATCHED)
        assertEquals(tickedCode, null)
    }

    @Test
    fun scratchedTickedHasScratchedAndNotActivatedStatusAndHasCode() {
        createNewTicket()

        val generatedUUID = UUID.randomUUID()

        ticketStatusViewModel.setTicketScratched(generatedUUID)

        updateValues()

        assertEquals(ticketStatus, TicketStatusEnum.SCRATCHED_NOT_ACTIVATED)
        assertEquals(tickedCode, generatedUUID)
    }

    @Test
    fun activatedTicketHasScratchedAndActivatedStatusAndHasCode() {
        createNewTicket()

        val generatedUUID = UUID.randomUUID()

        ticketStatusViewModel.setTicketScratched(generatedUUID)
        ticketStatusViewModel.setTicketActivated()

        updateValues()

        assertEquals(ticketStatus, TicketStatusEnum.SCRATCHED_ACTIVATED)
        assertEquals(tickedCode, generatedUUID)
    }

    @Test
    fun clearedTicketHasNotScratchedStatusAndCodeIsNull() {
        createNewTicket()

        val generatedUUID = UUID.randomUUID()

        ticketStatusViewModel.setTicketScratched(generatedUUID)
        ticketStatusViewModel.clearTicket()

        updateValues()

        assertEquals(ticketStatus, TicketStatusEnum.NOT_SCRATCHED)
        assertNull(tickedCode)

        updateValues()

        ticketStatusViewModel.setTicketScratched(generatedUUID)
        ticketStatusViewModel.setTicketActivated()
        ticketStatusViewModel.clearTicket()

        assertEquals(ticketStatus, TicketStatusEnum.NOT_SCRATCHED)
        assertNull(tickedCode)
    }

    private fun createNewTicket() {
        ticketStatusViewModel.clearTicket()
    }

    private fun updateValues() {
        currentTicketStatusUiState = ticketStatusViewModel.uiState.value
        ticketStatus = currentTicketStatusUiState.ticketStatus
        tickedCode = currentTicketStatusUiState.ticketCode
    }

}
