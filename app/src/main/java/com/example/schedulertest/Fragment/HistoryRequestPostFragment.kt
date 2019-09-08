package com.example.schedulertest.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedulertest.Adapter.HistoryRecyclerViewAdapter
import com.example.schedulertest.Data.HistoryData
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.fragment_more_history_request_post.*

class HistoryRequestPostFragment : Fragment() {

    lateinit var historyRecyclerViewAdapter: HistoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_history_request_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 추후 서버와의 통신으로 대체할 부분입니다 */
        var dataList: ArrayList<HistoryData> = ArrayList()
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )
        dataList.add(
            HistoryData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "제목","2019년 8월 12일","오후 1시~ 오후2시")
        )


        historyRecyclerViewAdapter = HistoryRecyclerViewAdapter(context!!, dataList)
        rv_history_request_post.adapter = historyRecyclerViewAdapter
        rv_history_request_post.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL,false)


    }
}

