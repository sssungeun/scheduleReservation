package com.example.schedulertest.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedulertest.Adapter.GroupManagingRecyclerViewAdapater
import com.example.schedulertest.Data.GroupSelectData
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.activity_more_group.*

class GroupManagingActivity : AppCompatActivity() {

    lateinit var groupManagingRecyclerViewAdapter: GroupManagingRecyclerViewAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_group)

        configureRecyclerView()

//        //  그룹추가 버튼
        iv_group_add.setOnClickListener{
                val intent = Intent(this, GroupSelectFriendActivity::class.java)
                startActivity(intent)
            }
        tv_group_add.setOnClickListener{
            val intent = Intent(this, GroupSelectFriendActivity::class.java)
            startActivity(intent)
        }

            //  친구추가 버튼
        iv_friend_add.setOnClickListener{
            val intent = Intent(this, GroupAddFriendActivity::class.java)
            startActivity(intent)
        }
        tv_friend_add.setOnClickListener{
            val intent = Intent(this, GroupAddFriendActivity::class.java)
            startActivity(intent)
        }
        iv_more_group_managing_btn_back.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun configureRecyclerView() {
        var dataList: ArrayList<GroupSelectData> = ArrayList()
        dataList.add(
                GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
            )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
                GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
                )
        dataList.add(
                GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
                )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )
        dataList.add(
            GroupSelectData("https://cdn.icon-icons.com/icons2/1875/PNG/128/users_120204.png","이성은")
        )


        groupManagingRecyclerViewAdapter = GroupManagingRecyclerViewAdapater(this, dataList)
        rv_more_group_list.adapter = groupManagingRecyclerViewAdapter
        rv_more_group_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}