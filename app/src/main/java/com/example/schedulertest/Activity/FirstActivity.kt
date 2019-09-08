package com.example.schedulertest.Activity

import android.widget.Toast
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import android.R
import android.R.attr.button
import android.widget.EditText
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {

    // 객체 생성
    internal lateinit var editText: EditText
    internal lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedulertest.R.layout.main)

        //객체 참조
        editText = findViewById(com.example.schedulertest.R.id.editText) as EditText
        button = findViewById(com.example.schedulertest.R.id.button) as Button

        // 버튼 클릭리스너
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                // 메인->세컨드로 전환하는 인텐트 정의
                val intent = Intent(this@FirstActivity, SecondActivity::class.java)

                // editText에 담긴 String을 getText()로 가져와 string에 담음
                val string = editText.text.toString()
                // intent에 부가데이터를 구분할 태그 "TagName"에 string을 담음
                intent.putExtra("TagName", string)
                // 액티비티 전환(인텐트와 태그로 정의한 요청코드)
                startActivityForResult(intent, REQUEST_CODE_SECOND)

            }
        })
    }

    // setResult()의 응답코드에 따라 다양한 이벤트를 설정할 수 있는 onActivityResult()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // SecondActivity로 부터 응답받은 코드가 200이면 아래의 토스트 메시지 출력
        if (resultCode == 200) {
            Toast.makeText(applicationContext, "정상적으로 반환", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        // 요청코드를 담은 태그
        val REQUEST_CODE_SECOND = 100
    }
}