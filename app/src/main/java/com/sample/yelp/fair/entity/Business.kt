package com.sample.yelp.fair.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Business : Serializable {
    @SerializedName("id")
    var id: String? = ""

    val alias: String? = ""

    val name: String? = ""

    @SerializedName("image_url")
    val imageUrl: String? = ""

    @SerializedName("is_claimed")
    val isClaimed: Boolean = false

    @SerializedName("is_closed")
    val isClosed: Boolean = false

    val url: String? = ""

    val phone: String? = ""

    @SerializedName("display_phone")
    val displayPhone: String? = ""

    @SerializedName("review_count")
    val reviewCount: Int = 0

    val categories: List<Category>? = null

    val rating: Float = 0.toFloat()

    val location: Location? = null

    val coordinates: Coordinates? = null

    val photos: List<String>? = null

    val hours: List<OpenHours>? = null

    val messaging: Messaging? = null

    inner class Messaging {
        private val url: String? = ""

        @SerializedName("use_case_text")
        private val useCaseText: String? = ""
    }
}
