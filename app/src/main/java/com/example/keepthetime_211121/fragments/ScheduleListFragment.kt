package com.example.keepthetime_211121.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_211121.EdtAppointmentActivity
import com.example.keepthetime_211121.R
import com.example.keepthetime_211121.adapters.ScheduleRecyclerAdapter
import com.example.keepthetime_211121.databinding.FragmentScheduleListBinding
import com.example.keepthetime_211121.datas.BasicResponse
import com.example.keepthetime_211121.datas.ScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleListFragment : BaseFragment() {

    lateinit var binding : FragmentScheduleListBinding

    val mScheduleList = ArrayList<ScheduleData>()
    lateinit var mScheduleAdapter : ScheduleRecyclerAdapter

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
        getScheduleLitFromServer()

        mScheduleAdapter = ScheduleRecyclerAdapter(mContext,mScheduleList)
        binding.appointmentRecyclerView.adapter = mScheduleAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getScheduleLitFromServer(){
        apiService.getRequestAppointment().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){

                    val br = response.body()!!

                    mScheduleList.clear()
                    mScheduleList.addAll(br.data.appointments)

                    mScheduleAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}