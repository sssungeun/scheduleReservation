package com.example.schedulertest.Network

import com.example.schedulertest.Network.GET.GetEmailCheckResponse
import com.example.schedulertest.Network.GET.GetNicknameCheckResponse
import com.example.schedulertest.Network.POST.PostLoginResponse
import com.example.schedulertest.Network.POST.PostResponse
import com.example.schedulertest.Network.POST.PostSignupResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {



    @POST("api/auth/signin")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLoginResponse>

    @POST("api/auth/signup")
    fun postSignupResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostSignupResponse>

    // 이메일 중복 확인
    @POST("/core/users/emailCheck")
    fun postEmailCheckResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ) : Call<PostResponse>

    @GET("core/users/emailCheck")
    fun getEmailCheckResponse(
        @Query("email") email : String?
    ) : Call<GetEmailCheckResponse>



    @GET("core/users/nicknameCheck")
    fun getNicknameCheckResponse(
        @Query("nickname") nickname : String?
    ) : Call<GetNicknameCheckResponse>


//    @GET("api/webtoons/main")
//    fun getMainProductListResponse(
//        @Header("content-type")content_type: String,
//        @Query("flag")  flag:Int
//    ): Call<GetMainProductListResponse>
}