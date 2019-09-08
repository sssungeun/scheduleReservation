package com.example.schedulertest.Activity

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.Exam
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainDetailActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedulertest.R.layout.activity_main_detail)


//        val nextIntent = Intent(this, MainDetailEditActivity::class.java)
//        //  nextIntent.putExtra("nameKey", "nachoi")
//        startActivity(nextIntent)

        //    var myExam = Exam("nachoi", 90, "A")
/* name, score, grade 데이터를 가지는 Exam 변수 생성*/


        btn_main_detail_close.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
           // nextIntent.putExtra("key", "value")
            startActivity(nextIntent)
            //val stringData = intent.getStringExtra("key")

        }

        btn_main_detail_edit.setOnClickListener {
            val nextIntent = Intent(this, MainDetailEditActivity::class.java)
            //  nextIntent.putExtra("examKey", myExam)
            startActivity(nextIntent)
            /* intent.putExtra 로 Exam 클래스의 변수 myExam을 저장 */
        }
        iv_main_detail_response.setOnClickListener {
            val nextIntent = Intent(this, MainDetailResponceActivity::class.java)
            //  nextIntent.putExtra("examKey", myExam)
            startActivity(nextIntent)
            /* intent.putExtra 로 Exam 클래스의 변수 myExam을 저장 */
        }

        tv_main_detail_response.setOnClickListener {
            val nextIntent = Intent(this, MainDetailResponceActivity::class.java)
            //  nextIntent.putExtra("examKey", myExam)
            startActivity(nextIntent)
            /* intent.putExtra 로 Exam 클래스의 변수 myExam을 저장 */
        }
//             삭제 > 팝업 후 삭제
//            btn_main_detail_garbage.setOnClickListener{
//                val nextIntent = Intent(this, MainDetailEditActivity::class.java)
//                //  nextIntent.putExtra("examKey", myExam)
//                startActivity(nextIntent)
//                /* intent.putExtra 로 Exam 클래스의 변수 myExam을 저장 */
//            }


//        btn_main_detail_edit.setOnClickListener{
//            val intent = Intent(this, MainDetailEditActivity::class.java)
//            intent.putExtra("value1","문자열") //인텐트에 데이터 ㅓㄱ재
//            intent.putExtra("value2", 1024)
//            intent.putExtra("value3", 36.7f)
//            startActivity(intent) //인텐트를 목적지 액티비티로 발송
//            val intent  = getIntent()
//        }





        }
    }
