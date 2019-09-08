package com.example.schedulertest.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.Adapter.MainDetailResponcePageAdapter
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.activity_main_detail_responce.*

class MainDetailResponceActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail_responce)
       // configureTitleBar()
        configureMainTab()


//        img_toolbar_main_action.setOnClickListener{
//            if(SharedPreferenceController.getUserID(this).isEmpty()){
//                startActivity<LoginActivity>()
//            }
//            else{
//                SharedPreferenceController.clearUserID(this)
//                configureTitleBar()
//
//            }
//        }
    }
//    override fun onResume() {
//        super.onResume()
//        configureTitleBar()
//    }
//    private fun configureTitleBar(){
//        if(SharedPreferenceController.getUserID(this).isEmpty()){
//            img_toolbar_main_action.isSelected = false
//        }
//        else{
//            img_toolbar_main_action.isSelected = true
//        }
//    }

    private fun configureMainTab() {
        vp_responce.adapter = MainDetailResponcePageAdapter(supportFragmentManager, 2)
        vp_responce.offscreenPageLimit = 2
        tl_responce_tabbar.setupWithViewPager(vp_responce)

        val navCategoryMainLayout: View = (this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.fragment_main_detail_responce_tabbar, null, false)
        tl_responce_tabbar.getTabAt(0)!!.customView = navCategoryMainLayout.
            findViewById(R.id.rl_tabbar_responce_yes) as RelativeLayout
        tl_responce_tabbar.getTabAt(1)!!.customView = navCategoryMainLayout.
            findViewById(R.id.rl_tabbar_responce_no) as RelativeLayout

    }
}













