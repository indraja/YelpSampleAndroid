package com.sample.yelp.fair.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class OpenHours : Serializable {
    @SerializedName("open")
    val openHour: List<OpenHour>? = null

    inner class OpenHour {
        @SerializedName("is_overnight")
        val isOvernight: Boolean = false

        val start: String? = null

        val end: String? = null

        val day: Int = 0
    }
}
