package com.example.schedulertest.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedulertest.Adapter.MainListRecyclerViewAdapter
import com.example.schedulertest.Data.MainListData
import com.example.schedulertest.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainListRecyclerViewAdapter: MainListRecyclerViewAdapter


    /**
     * 페이지가 열려 있는지 알기 위한 플래그
     */
    internal var isPageOpen = false

    /**
     * 애니메이션 객체
     */
    internal lateinit var translateLeftAnim: Animation
    internal lateinit var translateRightAnim: Animation

    /**
     * 슬라이딩으로 보여지는 페이지 레이아웃
     */
     lateinit var page: LinearLayout

    /**
     * 버튼
     */
    internal lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


//        configureTitleBar()
        configureRecyclerView()


        //  보내기 버튼
        btn_main_send.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        val intent = Intent(this, SendInputDetailActivity::class.java)
            startActivity(intent)
        }

        //  알림 버튼
        btn_main_notice.setOnClickListener{
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }



         //더보기 버튼  -옆창에서 나오는건 fragment같기도
//        btn_main_more.setOnClickListener{
//            val intent = Intent(this, NoticeActivity::class.java)
//            startActivity(intent)
//        }

        btn_main_home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //리사이클러뷰의 아이템을 클릭했을 때 액티비이 이동
        rv_main_list.setOnClickListener{
            val intent = Intent(this, MainDetailActivity::class.java)
            startActivity(intent)
        }

//        button = findViewById(R.id.button) as Button
//
//        // 슬라이딩으로 보여질 레이아웃 객체 참조
//        page = findViewById(R.id.page) as LinearLayout
//
//        // 애니메이션 객체 로딩
//        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left)
//        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right)
//
//        // 애니메이션 객체에 리스너 설정
//        val animListener = SlidingPageAnimationListener()
//        translateLeftAnim.setAnimationListener(animListener)
//        translateRightAnim.setAnimationListener(animListener)
//

    }




//        private fun configureTitleBar(){
//            img_send_detail_input_btn_back.setOnClickListener {
//                finish()
//            }
//            img_send_detail_input_btn_next.setOnClickListener{
//                finish()
//            }
//        }

//더보기 메뉴창
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true



}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
//        action_grouping.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

    }

    private inner class SlidingPageAnimationListener : Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        override fun onAnimationEnd(animation: Animation) {
            if (isPageOpen) {
                page.visibility = View.INVISIBLE

                button.text = "Open"
                isPageOpen = false
            } else {
                button.text = "Close"
                isPageOpen = true
            }
        }

        override fun onAnimationRepeat(animation: Animation) {

        }

        override fun onAnimationStart(animation: Animation) {

        }

    }


    private fun configureRecyclerView(){
        var dataList: ArrayList<MainListData> = ArrayList()
        dataList.add(MainListData("2019년 8월 19일 월요일" ,"제목","오후 1시 ~ 오후 2시",
            "https://svgsilh.com/png-1024/155853.png",3,5
            ))
        dataList.add(MainListData("2019년 8월 20일 화요일" ,"제목","오후 2시 ~ 오후 3시",
            "https://svgsilh.com/png-1024/155853.png",7,7
        ))
        dataList.add(MainListData("2019년 8월 21일 수요일" ,"제목","오후 3시 ~ 오후 4시",
            "https://svgsilh.com/png-1024/155853.png",4,4
        ))
        dataList.add(MainListData("2019년 8월 22일 목요일" ,"제목","오후 1시 ~ 오후 2시",
            "https://svgsilh.com/png-1024/155853.png",2,3
        ))
        dataList.add(MainListData("2019년 8월 23일 금요일" ,"약속","오후 2시 ~ 오후 3시",
            "https://svgsilh.com/png-1024/155853.png",1,2
        ))
        dataList.add(MainListData("2019년 8월 24일 토요일" ,"제목","오후 1시 ~ 오후 2시",
            "https://svgsilh.com/png-1024/155853.png",3,3
        ))
        dataList.add(MainListData("2019년 8월 25일 일요일" ,"제목","오후 1시 ~ 오후 2시",
            "https://svgsilh.com/png-1024/155853.png",3,3
        ))
        dataList.add(MainListData("2019년 8월 26일 월요일" ,"제목","오후 1시 ~ 오후 2시",
            "https://svgsilh.com/png-1024/155853.png",3,3
        ))

        mainListRecyclerViewAdapter = MainListRecyclerViewAdapter(this,dataList)
        rv_main_list.adapter = mainListRecyclerViewAdapter
        rv_main_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}

