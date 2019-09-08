package com.example.schedulertest.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.Adapter.HistoryPageAdapter
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.activity_more_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_history)

        configureMainTab()
    }

    private fun configureMainTab() {
        vp_history.adapter = HistoryPageAdapter(supportFragmentManager, 2)
        vp_history.offscreenPageLimit = 2
        tl_history_tabbar.setupWithViewPager(vp_history)

        val navCategoryMainLayout: View = (this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.fragment_more_history_tabbar, null, false)
        tl_history_tabbar.getTabAt(0)!!.customView = navCategoryMainLayout.
            findViewById(R.id.rl_tabbar_history_post) as RelativeLayout
        tl_history_tabbar.getTabAt(1)!!.customView = navCategoryMainLayout.
            findViewById(R.id.rl_tabbar_history_get) as RelativeLayout

    }
}

