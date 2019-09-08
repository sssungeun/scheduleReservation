package com.example.schedulertest.Network.GET


data class GetEmailCheckResponse (
        val status: Long,
        val success: Boolean,
        val message: String,
        val data: String
)