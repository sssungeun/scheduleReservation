package com.example.schedulertest.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedulertest.Adapter.GroupSelectFriendRecyclerViewAdapater
import com.example.schedulertest.Data.PersonSelectData
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.activity_more_group_friend_select.*

class GroupSelectFriendActivity : AppCompatActivity() {

    lateinit var groupSelectFriendRecyclerViewAdapter : GroupSelectFriendRecyclerViewAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_group_friend_select)

        configureRecyclerView()
//
        iv_more_group_select_friend_btn_back.setOnClickListener{
                val intent = Intent(this, GroupManagingActivity::class.java)
                startActivity(intent)
            }


        btn_group_add_next.setOnClickListener{
            val intent = Intent(this, GroupAddNameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<PersonSelectData> = ArrayList()
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )
        dataList.add(
            PersonSelectData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAukpV31V9zNtu0CQ0QOlJ_aelKU0A6sM-kokoqk6CQSHKPlMuRA",
                "이성은",false)
        )


        groupSelectFriendRecyclerViewAdapter = GroupSelectFriendRecyclerViewAdapater(this, dataList)
        rv_more_group_select_friend.adapter = groupSelectFriendRecyclerViewAdapter
        rv_more_group_select_friend.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}