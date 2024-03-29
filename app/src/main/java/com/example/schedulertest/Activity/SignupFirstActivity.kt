package com.example.schedulertest.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.schedulertest.Network.ApiClient
import com.example.schedulertest.Network.GET.GetEmailCheckResponse
import com.example.schedulertest.Network.GET.GetNicknameCheckResponse
import com.example.schedulertest.Network.NetworkService
import com.example.schedulertest.Network.POST.PostResponse
import com.example.schedulertest.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup_first.*
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder
import java.util.regex.Pattern

class SignupFirstActivity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    var imageUri: Uri? = null
    var emailCheckFlag = 0 // 실패
    private var image: MultipartBody.Part? = null
    var chkFlag: Boolean = false
    val passwdPattern: String = "^[A-Za-z[0-9]]{8,20}$" // 영문, 숫자
    val nicknamePattern: String = "^[A-Za-z[0-9]]{2,8}$" // 영문, 숫자
    var emailPattern: String = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
    var emailCheckValue: String = ""

    val authorization_info =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxMDUsImlhdCI6MTU2MjcyMjQ5MCwiZXhwIjoxNTY1MzE0NDkwfQ.CdVtW28EY4XOWV_xlt2dlYFMdEdFcIRN6lmsmJ8_jKQ"

    val networkService: NetworkService by lazy {
        ApiClient.getRetrofit().create(NetworkService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_first)

        // 특수문자, 영문, 숫자 조합 (8~10 자리)

        edit_signup_act_nickname.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_nickname.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btn_signup_act_next.visibility = View.VISIBLE
                if (nickNameCheckPattern(edit_signup_act_nickname.text.toString())) {
                    tv_signup_act_nickname_pattern_check.setText("검사 완료")
                    tv_signup_act_nickname_confirm.setTextColor(Color.parseColor("#00b6de"))
                } else {
                    tv_signup_act_nickname_pattern_check.setText("2-8글자 사이로 영어 숫자로만 입력해주세요")
                    tv_signup_act_nickname_confirm.setTextColor(Color.parseColor("#bdbdbd"))
                }
                // 입력되는 텍스트에 변화가 있을 때
            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // 입력하기 전에

            }

        })

        edit_signup_act_verify_num.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_verify_num.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // 입력되는 텍스트에 변화가 있을 때
                if (edit_signup_act_verify_num.text.toString() == "") {
                    tv_signup_act_verify_num_confirm.setTextColor(Color.parseColor("#bdbdbd"))
                } else {
                    tv_signup_act_verify_num_confirm.setTextColor(Color.parseColor("#00b6de"))
                }
            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // 입력하기 전에
            }
        })

        edit_signup_act_email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_email.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btn_signup_act_next.visibility = View.VISIBLE
                if (emailCheckPattern(edit_signup_act_email.text.toString())) {
                    postEmailCheckResponse(edit_signup_act_email.text.toString())
                    tv_signup_act_email_pattern_check.setText("검사 완료")
                    tv_signup_act_email_confirm.setTextColor(Color.parseColor("#00b6de"))
                } else {
                    tv_signup_act_email_pattern_check.setText("이메일 양식에 맞게 작성해주세요")
                    tv_signup_act_email_confirm.setTextColor(Color.parseColor("#bdbdbd"))
                }
                // 입력되는 텍스트에 변화가 있을 때
            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // 입력하기 전에
            }
        })

        edit_signup_act_passwd.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundResource(R.drawable.signup_focus_on)
            else v.setBackgroundResource(R.drawable.signup_border_off)
        }

        edit_signup_act_passwd.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (passwCheckPattern(edit_signup_act_passwd.text.toString())) {
                    tv_signup_act_passwd_check_pattern.setText("검사 완료")
                    btn_signup_act_next.visibility = View.VISIBLE
                } else {
                    tv_signup_act_passwd_check_pattern.setText("8-20글자 사이로 영어 숫자로만 입력해주세요")
                    btn_signup_act_next.visibility = View.GONE
                }
                // 입력되는 텍스트에 변화가 있을 때
            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // 입력하기 전에

            }

        })

        tv_signup_act_email_confirm.setOnClickListener {
            if (edit_signup_act_email.text.toString() == "") {
                edit_signup_act_email.requestFocus()
                Toast.makeText(applicationContext, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            } else {
                ll_signup_act_verify_num.visibility = View.VISIBLE
                tv_signup_act_email_confirm.visibility = View.GONE
                edit_signup_act_verify_num.requestFocus()

            }

        }

        tv_signup_act_verify_num_confirm.setOnClickListener {
            if (edit_signup_act_verify_num.text.toString() == "") {
                edit_signup_act_verify_num.requestFocus()
                Toast.makeText(applicationContext, "인증번호를 입력해주세요", Toast.LENGTH_LONG).show()
            } else {

                ll_signup_act_nickname.visibility = View.VISIBLE
                tv_signup_act_verify_num_confirm.visibility = View.GONE
                edit_signup_act_nickname.requestFocus()
                if (emailCheckValue.equals(edit_signup_act_verify_num.toString())) {

                    //통신

                } else {
                    Toast.makeText(applicationContext, "인증 번호를 다시 입력해주세요", Toast.LENGTH_LONG).show()
                }
            }
        }

        tv_signup_act_nickname_confirm.setOnClickListener {
            if (edit_signup_act_nickname.text.toString() == "") {
                edit_signup_act_nickname.requestFocus()
                Toast.makeText(applicationContext, "닉네임을 입력해주세요", Toast.LENGTH_LONG).show()
            } else {
                getNicknameCheck()

            }
        }

        rl_signup_act_profile.setOnClickListener {
            changeImage()
        }

        edit_signup_act_passwd.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btn_signup_act_next.visibility = View.VISIBLE
                // 입력되는 텍스트에 변화가 있을 때

            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // 입력하기 전에
            }

        })
    }

    fun passwCheckPattern(pwd: String): Boolean {

        var match = Pattern.compile(passwdPattern).matcher(pwd);
        if (match.find()) {
            chkFlag = true
        } else {
            chkFlag = false
        }
        return chkFlag
    }

    fun nickNameCheckPattern(nickName: String): Boolean {

        var match = Pattern.compile(nicknamePattern).matcher(nickName);
        if (match.find()) {
            chkFlag = true
        } else {
            chkFlag = false
        }
        return chkFlag
    }

    fun emailCheckPattern(email: String): Boolean {

        var match = Pattern.compile(emailPattern).matcher(email);
        if (match.find()) {
            chkFlag = true
        } else {
            chkFlag = false
        }
        return chkFlag
    }


    // 갤러리로부터 이미지 갖고올 때 사용하는 오버라이딩 메소드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    this.data = data!!.data!!
                    imageUri = data!!.data


                    // 선택한 이미지를 해당 이미지뷰에 적용
                    Glide.with(this)
                        .load(data.data)
                        .centerCrop()
                        .into(img_signup_act_profile)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }


    // 이미지 파일을 확장자까지 표시해주는 메소드
    fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }

    // 방 배경 이미지 변경
    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }


    //이메일 확인
    fun postEmailCheckResponse(email: String) {

        var jsonObject = JSONObject()
        jsonObject.put("email", email)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postEmailCheckResponse = networkService.postEmailCheckResponse("application/json", gsonObject)
        postEmailCheckResponse.enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful) {
                    Log.e("이메일인증","response"+ response.body().toString())
                    if (response.isSuccessful) {
                        Log.v("asdf", "이메일 응답 = " + response.body()!!.message)

                        if (response.body()!!.success) {
                            var data = response!!.body()!!.message
                            Log.v("asdf", "이메일 인증 번호 = " + data)

                        } else {
                            Toast.makeText(applicationContext, "중복된 이메일입니다", Toast.LENGTH_LONG).show()
                        }

                    } else {
                        Log.v("ASdf", "테스트 에러 = " + response.code())
                    }

                }
            }

//            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
//                toast("서버연결 오류")
//            }

        })


    }

    fun getEmailCheck() {
        val getEmailCheckResponse =
            networkService.getEmailCheckResponse(URLEncoder.encode(edit_signup_act_email.text.toString(), "utf-8"))
        getEmailCheckResponse.enqueue(object : Callback<GetEmailCheckResponse> {

            override fun onResponse(call: Call<GetEmailCheckResponse>, response: Response<GetEmailCheckResponse>) {
                Log.v("TAG", "이메일 인증 통신 성공 = " + edit_signup_act_email.text.toString())

                if (response.isSuccessful) {
                    Log.v("asdf", "이메일 응답 = " + response.body()!!.message)

                    if (response.body()!!.success) {
                        var data = response!!.body()!!.data
                        Log.v("asdf", "이메일 인증 번호 = " + data)
                        emailCheckValue = data

                    } else {
                        Toast.makeText(applicationContext, "중복된 이메일입니다", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Log.v("ASdf", "테스트 에러 = " + response.code())
                }
            }

            override fun onFailure(call: Call<GetEmailCheckResponse>, t: Throwable?) {
                Toast.makeText(applicationContext, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun getNicknameCheck() {
        val getNicknameCheckResponse = networkService.getNicknameCheckResponse(edit_signup_act_nickname.text.toString())
        getNicknameCheckResponse.enqueue(object : Callback<GetNicknameCheckResponse> {

            override fun onResponse(
                call: Call<GetNicknameCheckResponse>,
                response: Response<GetNicknameCheckResponse>
            ) {
                Log.v("TAG", "닉네임 인증 통신 성공")
                if (response.isSuccessful) {
                    if (response.body()!!.success) {
                        Log.v("asdf", "닉네임 중복 X")
                        ll_signup_act_passwd.visibility = View.VISIBLE
                        tv_signup_act_nickname_confirm.visibility = View.GONE
                        edit_signup_act_passwd.requestFocus();
                    } else {
                        Toast.makeText(applicationContext, "중복된 닉네임입니다", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Log.v("ASdf", "테스트 에러 = " + response.code())
                }
            }

            override fun onFailure(call: Call<GetNicknameCheckResponse>, t: Throwable?) {
                Toast.makeText(applicationContext, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
