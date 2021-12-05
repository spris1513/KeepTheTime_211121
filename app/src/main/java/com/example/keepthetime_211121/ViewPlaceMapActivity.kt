package com.example.keepthetime_211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityViewPlaceMapBinding

class ViewPlaceMapActivity : BaseActivity() {

    lateinit var binding : ActivityViewPlaceMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view_place_map)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        0. 프로젝트에 네이버지도 설치(완료)

//        1. 화면(xml)에 네이버 맵 띄워주기

//        2. 네이버맵 객체를 실제로 얻어내기 > getMapAsync

//        3. 카메라 이동 / 마커 추가

    }
}