package com.jjsan.scratchticket.api

import java.util.UUID

class TicketActivation {

    companion object {
        const val MINIMAL_VALUE = 80000
    }

    suspend fun activateTicket(
        ticketCode: UUID
    ): Boolean {
        val apiInterface = getApiInterface()

        try {
            val response = apiInterface.activateTicket(ticketCode.toString())

            if (response.isSuccessful) {
                val responseCode = response.body()?.android?.toInt()
                if (responseCode != null) {
                    return responseCode > MINIMAL_VALUE
                }
            }
        } catch (ex: Exception) {
            println(ex.message)
        }

        return false
    }

    private fun getApiInterface(): TicketActivationApi {
        val retrofit = RetrofitClient.getClient()
        return retrofit.create(TicketActivationApi::class.java)
    }

}
