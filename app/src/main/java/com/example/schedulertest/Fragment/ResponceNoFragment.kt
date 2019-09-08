package com.example.schedulertest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedulertest.Adapter.ResponceRecyclerViewAdapter
import com.example.schedulertest.Data.ResponceData
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.fragment_main_detail_list_responce_no.*

class ResponceNoFragment : Fragment() {

    lateinit var responceRecyclerViewAdapter: ResponceRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_detail_list_responce_no, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 추후 서버와의 통신으로 대체할 부분입니다 */
        var dataList: ArrayList<ResponceData> = ArrayList()
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )
        dataList.add(
            ResponceData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은")
        )

        responceRecyclerViewAdapter = ResponceRecyclerViewAdapter(context!!, dataList)
        rv_main_detail_responce_no.adapter = responceRecyclerViewAdapter
        rv_main_detail_responce_no.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL,false)


    }
}

