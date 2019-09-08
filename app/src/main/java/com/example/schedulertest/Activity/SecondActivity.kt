package com.example.schedulertest.Activity

import android.content.Intent
import android.content.Intent.getIntent
import android.R
import android.R.attr.button
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    // 객체 생성
    internal lateinit var textView: TextView
    internal lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedulertest.R.layout.second)

        // 객체 참조
        textView = findViewById(com.example.schedulertest.R.id.textView) as TextView
        button = findViewById(com.example.schedulertest.R.id.button) as Button

        // 인텐트를 받아오는 getIntent()를 이용하여 getIntent 정의
        val getIntent = intent
        // 받아온 인텐트에 담긴 부가데이터를 받아옴(구분을 위한 키 입력)
        val string = getIntent.extras!!.getString("TagName")
        // 받아온 데이터를 textView에 뿌림
        textView.text = string

        // 버튼 클릭리스너
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // 버튼 클릭시 원래의 액티비티로 돌아가는 인텐트 정의
                val passedIntent = Intent()
                // 응답코드는 200으로 전함, 태그로 정의하지 않고 바로 숫자로 응답코드를 기입하여도 됨.
                setResult(200, passedIntent)
                // SecondActivity 소멸
                finish()
            }
        })
    }
}
