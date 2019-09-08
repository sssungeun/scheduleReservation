package com.example.schedulertest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.schedulertest.Adapter.SendSelectGroupRecyclerViewAdapter
import com.example.schedulertest.Data.GroupSelectData

import com.example.schedulertest.R

import kotlinx.android.synthetic.main.fragment_send_select_group.*

class SendSelectGroupFragment  : Fragment() {

    lateinit var sendSelectGroupRecyclerViewAdapter: SendSelectGroupRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_select_group, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 추후 서버와의 통신으로 대체할 부분입니다 */
        var selectDataList: ArrayList<GroupSelectData> = ArrayList()
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        selectDataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )


        sendSelectGroupRecyclerViewAdapter = SendSelectGroupRecyclerViewAdapter(context!!, selectDataList)
        rv_send_select_group.adapter = sendSelectGroupRecyclerViewAdapter
        rv_send_select_group.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL,false)


    }
}
