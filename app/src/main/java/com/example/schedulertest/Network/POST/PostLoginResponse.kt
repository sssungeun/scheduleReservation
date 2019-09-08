package com.example.schedulertest.Network.POST
data class PostLoginResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data:String?
)