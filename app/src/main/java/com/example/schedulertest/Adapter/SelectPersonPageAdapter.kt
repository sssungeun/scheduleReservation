package com.example.schedulertest.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.schedulertest.Fragment.SendSelectGroupFragment
import com.example.schedulertest.Fragment.SendSelectPersonFragment

class SelectPersonPageAdapter (fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> SendSelectPersonFragment()
            1 -> SendSelectGroupFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}