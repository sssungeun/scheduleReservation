package com.example.schedulertest.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.schedulertest.Fragment.ResponceNoFragment
import com.example.schedulertest.Fragment.ResponceYesFragment

class MainDetailResponcePageAdapter (fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){
//    companion object {
//        private var responceYesFragment: ResponceYesFragment? = null
//        private var responceNoFragment: ResponceNoFragment? = null
//
//        @Synchronized
//        fun getResponceYesFragment(): ResponceYesFragment{
//            if(responceYesFragment == null) responceYesFragment = ResponceYesFragment()
//            return responceYesFragment!!
//        }
//        @Synchronized
//        fun getResponceNoFragment(): ResponceNoFragment{
//            if(responceNoFragment == null) responceNoFragment = ResponceNoFragment()
//            return responceNoFragment!!
//        }
//
//    }
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> ResponceYesFragment()
            1 -> ResponceNoFragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}