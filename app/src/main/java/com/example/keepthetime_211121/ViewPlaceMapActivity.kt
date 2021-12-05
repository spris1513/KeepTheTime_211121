package com.example.keepthetime_211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityViewPlaceMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker

class ViewPlaceMapActivity : BaseActivity() {

    lateinit var binding : ActivityViewPlaceMapBinding

    var mSelectedLatLng : LatLng? = null
    var mSelectedMarker : Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_view_place_map)
        binding.naverMapView.onCreate(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        0. 프로젝트에 네이버지도 설치(완료)

//        1. 화면(xml)에 네이버 맵 띄워주기(완료)

//        2. 네이버맵 객체를 실제로 얻어내기 > getMapAsync

        binding.naverMapView.getMapAsync {

            val naverMap = it



        }

//        3. 카메라 이동 / 마커 추가
    }
    override fun onStart() {
        super.onStart()
        binding.naverMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.naverMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.naverMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.naverMapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.naverMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.naverMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.naverMapView.onLowMemory()
    }
}