package com.example.schedulertest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schedulertest.Data.HistoryData
import com.example.schedulertest.Fragment.HistoryRequestGetFragment
import com.example.schedulertest.R

class HistoryRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<HistoryData>): RecyclerView.Adapter<HistoryRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_history_list_detail, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].profileImg)
            .into(holder.profileImg)
        holder.listDate.text = dataList[position].listDate
        holder.listTitle.text= dataList[position].listTitle
        holder.listTime.text= dataList[position].listTime

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listDate = itemView.findViewById(R.id.tv_rv_history_date) as TextView
        var listTitle = itemView.findViewById(R.id.tv_rv_history_title) as TextView
        var listTime = itemView.findViewById(R.id.tv_rv_history_time) as TextView
        var profileImg = itemView.findViewById(R.id.iv_rv_history_profile) as ImageView
    }
}