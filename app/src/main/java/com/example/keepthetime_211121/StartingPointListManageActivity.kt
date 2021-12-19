package com.example.keepthetime_211121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityStartingPointListManageBinding

class StartingPointListManageActivity : BaseActivity() {

    lateinit var binding : ActivityStartingPointListManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_starting_point_list_manage)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddStartingPoint.setOnClickListener {

            val myIntent = Intent(mContext,EditStartingPointActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

    }
}