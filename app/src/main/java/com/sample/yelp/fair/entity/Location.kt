package com.sample.yelp.fair.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Location : Serializable {
    val address1: String? = null

    val address2: String? = null

    val address3: String? = null

    val city: String? = null

    @SerializedName("zip_code")
    val zipCode: String? = null

    private val country: String? = null

    private val state: String? = null

    @SerializedName("display_address")
    val displayAddress: List<String>? = null

    @SerializedName("cross_streets")
    val crossStreets: String? = null
}
