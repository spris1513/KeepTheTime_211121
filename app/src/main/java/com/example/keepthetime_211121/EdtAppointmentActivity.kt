package com.example.keepthetime_211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityEdtAppointmentBinding

class EdtAppointmentActivity : BaseActivity() {

    lateinit var binding : ActivityEdtAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edt_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}