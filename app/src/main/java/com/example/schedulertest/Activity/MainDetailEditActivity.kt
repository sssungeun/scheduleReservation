package com.example.schedulertest.Activity

import androidx.core.app.NotificationCompat.getExtras
import android.content.Intent.getIntent
import android.content.Intent
import android.R
import android.R.*
import android.widget.TextView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.Exam
import kotlinx.android.synthetic.main.activity_main_detail_edit.*


class MainDetailEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedulertest.R.layout.activity_main_detail_edit)

//        if (intent.hasExtra("nameKey")) {
//            textView.text = intent.getStringExtra("nameKey")
//            /* "nameKey"라는 이름의 key에 저장된 값이 있다면
//               textView의 내용을 "nameKey" key에서 꺼내온 값으로 바꾼다 */
//
//        } else {
//            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
//        }

        btn_main_detail_edit_close.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            // nextIntent.putExtra("examKey", myExam)
            startActivity(nextIntent)
        }
        btn_main_detail_edit_done.setOnClickListener {
            val nextIntent = Intent(this, MainDetailActivity::class.java)
            // nextIntent.putExtra("examKey", myExam)
            startActivity(nextIntent)
        }


    }

}