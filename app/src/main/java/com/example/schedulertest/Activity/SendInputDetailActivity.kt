package com.example.schedulertest.Activity

import java.util.Calendar

import java.util.GregorianCalendar


import android.app.Activity

import android.app.DatePickerDialog

import android.app.TimePickerDialog
import android.content.Intent

import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText

import android.widget.TextView

import com.example.schedulertest.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_send_detail_input.*

//date,timepickerTest
class SendInputDetailActivity : Activity() {

    internal var mYear: Int = 0
    internal var mMonth: Int = 0
    internal var mDay: Int = 0
    internal var mHour: Int = 0
    internal var mMinute: Int = 0

    internal lateinit var mTxtDate: TextView

    internal lateinit var mTxtTime: TextView

//주소값
    val SEARCH_ADDRESS_ACTIVITY = 10000

    var et_address: EditText? = null


    //날짜 대화상자 리스너 부분

    internal var mDateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub

            //사용자가 입력한 값을 가져온뒤

            mYear = year

            mMonth = monthOfYear

            mDay = dayOfMonth

            //텍스트뷰의 값을 업데이트함

            UpdateNow()
        }


    //시간 대화상자 리스너 부분

    internal var mTimeSetListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            // TODO Auto-generated method stub

            //사용자가 입력한 값을 가져온뒤

            mHour = hourOfDay

            mMinute = minute


            //텍스트뷰의 값을 업데이트함

            UpdateNow()
        }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_send_detail_input)


        //텍스트뷰 2개 연결

        mTxtDate = findViewById<View>(R.id.txtdate) as TextView

        mTxtTime = findViewById<View>(R.id.txttime) as TextView


        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언

        val cal = GregorianCalendar()

        mYear = cal.get(Calendar.YEAR)

        mMonth = cal.get(Calendar.MONTH)

        mDay = cal.get(Calendar.DAY_OF_MONTH)

        mHour = cal.get(Calendar.HOUR_OF_DAY)

        mMinute = cal.get(Calendar.MINUTE)



        UpdateNow()//화면에 텍스트뷰에 업데이트 해줌.

        btn_send_detail_input_btn_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_send_detail_input_btn_next.setOnClickListener {
            val intent = Intent(this, SendSelectPersonActivity::class.java)
            startActivity(intent)
        }


        et_address = findViewById(R.id.txtplace) as EditText

        val btnchangeplace = findViewById(R.id.btnchangeplace) as Button

//
        if (btnchangeplace != null)
            btnchangeplace.setOnClickListener(View.OnClickListener {
                val i = Intent(this@SendInputDetailActivity, WebViewActivity::class.java)
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY)
            })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {

        super.onActivityResult(requestCode, resultCode, intent)

        when (requestCode) {

            SEARCH_ADDRESS_ACTIVITY ->

                if (resultCode == RESULT_OK) {

                    val data = intent.extras!!.getString("data")
                    if (data != null)
                        et_address!!.setText(data)

                }
        }
    }

        fun mOnClick(v: View) {

            when (v.id) {

                //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌

                R.id.btnchangedate ->

                    //여기서 리스너도 등록함

                    DatePickerDialog(
                        this@SendInputDetailActivity, mDateSetListener, mYear,

                        mMonth, mDay
                    ).show()


                //시간 대화상자 버튼이 눌리면 대화상자를 보여줌

                R.id.btnchangetime ->

                    //여기서 리스너도 등록함

                    TimePickerDialog(
                        this@SendInputDetailActivity, mTimeSetListener, mHour,

                        mMinute, false
                    ).show()
            }

        }


        //텍스트뷰의 값을 업데이트 하는 메소드

        internal fun UpdateNow() {

            mTxtDate.text = String.format(
                "%d/%d/%d", mYear,

                mMonth + 1, mDay
            )

            mTxtTime.text = String.format("%d:%d", mHour, mMinute)


        }

//    switchView.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
//        override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
//
//            if(isChecked) {
//                //체크된 상태 취소 시 코드
//            } else {
//                //체크된 상태로 만들 시 코드
//            }
//        }
//
//    })

}


