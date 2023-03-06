package com.jjsan.scratchticket

import com.jjsan.scratchticket.api.RepositoryConstants
import com.jjsan.scratchticket.api.RetrofitClient
import org.junit.Test
import retrofit2.Retrofit


class RetrofitClientTest {

    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = RetrofitClient.getClient()
        assert(instance.baseUrl().url().toString() == RepositoryConstants.SERVER_URL)
    }

}