package com.thiqah.postsapp.repository


import com.google.gson.annotations.SerializedName

data class PostBody(
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)