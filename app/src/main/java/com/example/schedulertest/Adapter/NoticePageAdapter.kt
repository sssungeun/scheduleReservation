package com.example.schedulertest.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.schedulertest.Fragment.NoticeRequestFragment
import com.example.schedulertest.Fragment.NoticeResponceFragment

class NoticePageAdapter (fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> NoticeResponceFragment()
            1 -> NoticeRequestFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}