package com.example.schedulertest.Activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulertest.R
import kotlinx.android.synthetic.main.activity_more_group_friend_add.*
import kotlinx.android.synthetic.main.activity_more_group_friend_add.toolbar

class GroupAddFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_group_friend_add)


        iv_more_group_add_friend_btn_back.setOnClickListener{
            val intent = Intent(this, GroupManagingActivity::class.java)
            startActivity(intent)
        }

    }
}