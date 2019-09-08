package com.example.schedulertest.Activity

import android.R
import android.widget.TextView
import android.widget.EditText
import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Button
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


class ServerActivity : Activity() {    //메인 activity 시작!

    private var socket: Socket? = null  //소켓생성
    lateinit var `in`: BufferedReader      //서버로부터 온 데이터를 읽는다.
    lateinit var out: PrintWriter        //서버에 데이터를 전송한다.
    lateinit var input: EditText         //화면구성
    lateinit var button: Button          //화면구성
    lateinit var output: TextView        //화면구성
    lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {   //앱 시작시  초기화설정
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedulertest.R.layout.activity_client)
        //start
        input = findViewById<View>(com.example.schedulertest.R.id.input) as EditText // 글자입력칸을 찾는다.
        button = findViewById<View>(com.example.schedulertest.R.id.button) as Button // 버튼을 찾는다.
        output = findViewById<View>(com.example.schedulertest.R.id.output) as TextView // 글자출력칸을 찾는다.
        // 버튼을 누르는 이벤트 발생, 이벤트 제어문이기 때문에 이벤트 발생 때마다 발동된다. 시스템이 처리하는 부분이 무한루프문에
        //있더라도 이벤트가 발생하면 자동으로 실행된다.

             fun onClick(v: View) {
                //버튼이 클릭되면 소켓에 데이터를 출력한다.
                val data = input.text.toString() //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
                Log.w("NETWORK", " $data")
                if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
                    out.println(data) //data를   stream 형태로 변형하여 전송.  변환내용은 쓰레드에 담겨 있다.
                }
            }


        val worker = object : Thread() {    //worker 를 Thread 로 생성
            override fun run() { //스레드 실행구문
                try {
                    //소켓을 생성하고 입출력 스트립을 소켓에 연결한다.
                    socket = Socket("192.168.1.7", 3000) //소켓생성
                    out = PrintWriter(
                        socket!!.getOutputStream(),
                        true
                    ) //데이터를 전송시 stream 형태로 변환하여                                                                                                                       //전송한다.
                    `in` = BufferedReader(
                        InputStreamReader(
                            socket!!.getInputStream()
                        )
                    ) //데이터 수신시 stream을 받아들인다.

                } catch (e: IOException) {
                    e.printStackTrace()
                }

                //소켓에서 데이터를 읽어서 화면에 표시한다.
                try {
                    while (true) {
                        data = `in`.readLine() // in으로 받은 데이타를 String 형태로 읽어 data 에 저장
                        output.post {
                            output.text = data //글자출력칸에 서버가 보낸 메시지를 받는다.
                        }
                    }
                } catch (e: Exception) {
                }

            }
        }
        worker.start()  //onResume()에서 실행.
    }

    override fun onStop() {  //앱 종료시
        super.onStop()
        try {
            socket!!.close() //소켓을 닫는다.
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}




