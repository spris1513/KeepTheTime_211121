package com.example.keepthetime_211121.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.keepthetime_211121.EdtAppointmentActivity
import com.example.keepthetime_211121.R
import com.example.keepthetime_211121.databinding.FragmentScheduleListBinding

class ScheduleListFragment : BaseFragment() {

    lateinit var binding : FragmentScheduleListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_list,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnAddAppointment.setOnClickListener {

            val myIntent = Intent(mContext,EdtAppointmentActivity::class.java)
            startActivity(myIntent)

        }
    }

    override fun setValues() {

    }
}