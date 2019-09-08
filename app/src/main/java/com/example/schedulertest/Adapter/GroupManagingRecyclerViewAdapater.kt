package com.example.schedulertest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schedulertest.Data.GroupSelectData
import com.example.schedulertest.R

class GroupManagingRecyclerViewAdapater (val ctx: Context, val dataList: ArrayList<GroupSelectData>): RecyclerView.Adapter<GroupManagingRecyclerViewAdapater.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_select_group, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].profileImg)
            .into(holder.profileImg)
        holder.profileName.text = dataList[position].profileName

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileName = itemView.findViewById(R.id.tv_rv_select_group_profile_name) as TextView
        var profileImg = itemView.findViewById(R.id.iv_rv_select_group_profile) as ImageView
    }
}
