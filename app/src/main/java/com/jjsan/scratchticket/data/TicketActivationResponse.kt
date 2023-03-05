package com.jjsan.scratchticket.data

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class TicketActivationResponse(

    @JsonProperty("android")
    val android: String,

    @JsonProperty("androidTM")
    @JsonIgnore
    val androidTM: String,

    @JsonProperty("androidRA")
    @JsonIgnore
    val androidRA: String,

    @JsonProperty("ios")
    @JsonIgnore
    val responseCodeForIos: String,

    @JsonProperty("iosTM")
    @JsonIgnore
    val iosTM: String,

    @JsonProperty("iosRA")
    @JsonIgnore
    val iosRA: String,

    @JsonProperty("iosRA_2")
    @JsonIgnore
    val iosRA2: String

)
