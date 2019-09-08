package com.example.schedulertest.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.Network.ApplicationController
import com.example.schedulertest.Network.NetworkService
import com.example.schedulertest.Network.POST.PostSignupResponse
import com.example.schedulertest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {
//network 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        val s_time = intent.getStringExtra("start_time")
//        toast("Current time: ${s_time}")

        val edtOnFocusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtSignupName.setOnFocusChangeListener(edtOnFocusChangeListener)
        edtSignupID.setOnFocusChangeListener(edtOnFocusChangeListener)
        edtSignupPW.setOnFocusChangeListener(edtOnFocusChangeListener)

        //회원가입 버튼이 클릭된다면
        btnSignupSubmit.setOnClickListener {
            val signup_u_name: String = edtSignupName.text.toString()
            val signup_u_id = edtSignupID.text.toString()
            val signup_u_pw: String = edtSignupPW.text.toString()

            //ID,PW가 공백이 아닌지 확인 후에
            if (isValid(signup_u_id, signup_u_pw, signup_u_name)) {
                postSignupResponse(signup_u_id, signup_u_pw, signup_u_name)
            }
        }
    }

    fun isValid(u_id: String, u_pw: String, u_name: String): Boolean {
        if (u_name == "") edtSignupName.requestFocus()
        else if (u_id == "") edtSignupID.requestFocus()
        else if (u_pw == "") edtSignupPW.requestFocus()
        else return true
        return false
    }

    fun postSignupResponse(u_id: String, u_pw: String, u_name: String) {
        // Request Signup

        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)
        jsonObject.put("name", u_name)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postSignupResponse: Call<PostSignupResponse> =
            networkService.postSignupResponse("application/json", gsonObject)
        postSignupResponse.enqueue(object : Callback<PostSignupResponse> {

            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                Log.e("Sugnup failed", t.toString())
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if (response.isSuccessful) {

                    if (response.body()!!.status == 201) {
                        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val e_time = simpleDateFormat.format(Date())

                        val intent: Intent = Intent()
                        intent.putExtra("end_time", e_time)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            }
        })
    }
}

