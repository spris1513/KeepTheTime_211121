package com.example.keepthetime_211121.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.keepthetime_211121.utils.ContextUtil

abstract class BaseFragment : Fragment() {

    lateinit var mContext : Context

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext= requireContext()
    }

    abstract fun setupEvents()
    abstract fun setValues()

}