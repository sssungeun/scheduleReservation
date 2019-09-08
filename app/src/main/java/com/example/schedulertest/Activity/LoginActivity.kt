package com.example.schedulertest.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.DB.SharedPreferenceController
import com.example.schedulertest.Network.ApplicationController
import com.example.schedulertest.Network.NetworkService
import com.example.schedulertest.Network.POST.PostLoginResponse
import com.example.schedulertest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val REQUEST_CODE_LOGIN_ACTIVITY = 1000


    //networkInterface 불러오기
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtLoginID.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtLoginPW.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        //로그인 버튼이 클릭된다면
        btnLoginSubmit.setOnClickListener {
            val login_u_id = edtLoginID.text.toString()
            val login_u_pw: String = edtLoginPW.text.toString()

           //id, pw가 공백이 아닌지 확인 후에
            if(isValid(login_u_id,login_u_pw)) postLoginResponse(login_u_id,login_u_pw)
        }

        txtLoginSignup.setOnClickListener{
            val intent: Intent = Intent(this, SignupFirstActivity::class.java)
            startActivity(intent)
        }

    }

    private fun isValid(u_id:String, u_pw:String):Boolean{
        if(u_id == "") edtLoginID.requestFocus()
        else if(u_pw == "") edtLoginPW.requestFocus()
        else return true
        return false
    }
//loginActivity에서 NetworkInterface에 통신을 요청
    fun postLoginResponse(u_id: String, u_pw: String){
//id, pw로 json객체를 만든 뒤
        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postLoginResponse: Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", gsonObject)
        postLoginResponse.enqueue(object : Callback<PostLoginResponse> {

            //HTTP Response를 받지 못했을 때,
            override fun onFailure(call: Call<PostLoginResponse>, t:Throwable){
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>){
                if(response.isSuccessful){
                    if(response.body()!!.status == 201){

                        // Request Login
                        SharedPreferenceController.setUserToken(applicationContext, response.body()!!.data!!)
                        finish()
                    }
                }
            }
        })
    }
}























