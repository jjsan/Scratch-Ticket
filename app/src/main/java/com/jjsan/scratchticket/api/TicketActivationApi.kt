package com.jjsan.scratchticket.api

import com.jjsan.scratchticket.data.TicketActivationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketActivationApi {

    companion object {
        const val CODE = "code"
    }

    @GET(RepositoryConstants.API_ACTIVATE_TICKET)
    suspend fun activateTicket(
        @Query(CODE) ticketCode: String
    ): Response<TicketActivationResponse>

}
