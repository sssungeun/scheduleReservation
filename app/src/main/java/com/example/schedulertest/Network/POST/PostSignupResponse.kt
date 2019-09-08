package com.example.schedulertest.Network.POST
data class PostSignupResponse(
     val status: Int,
     val success: Boolean,
     val message: String,
     val data  : String?
)