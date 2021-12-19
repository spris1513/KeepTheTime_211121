package com.example.keepthetime_211121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityEditStartingPointBinding
import com.naver.maps.map.overlay.Marker

class EditStartingPointActivity : BaseActivity() {

    lateinit var binding:ActivityEditStartingPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_starting_point)
        binding.naverMapView.onCreate(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

            val naverMap = it

//            지도의 한 곳을 클릭하면 > 마커를 추가


            naverMap.setOnMapClickListener { pointF, latLng ->

//                클랙 될 때 마다 생성자 호출 > 매번 새 마커를 그려주고있다.
//                단 하나의 마커만 유지 > 아직 안그려졌을때만 생성하자

                val marker = Marker()
                marker.position = latLng
                marker.map = naverMap

            }

        }

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
