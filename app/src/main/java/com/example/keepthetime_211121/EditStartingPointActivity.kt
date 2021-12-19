package com.example.keepthetime_211121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityEditStartingPointBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker

class EditStartingPointActivity : BaseActivity() {

    lateinit var binding:ActivityEditStartingPointBinding


    var mSelectedMarker : Marker? = null

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
//                위치는 매번 클릭될 때 마다 설정 ( EditAppointmentActivity 참고)

                if (mSelectedMarker == null){
//                    멤버변수로 만들어둔 마커가 null일때만 생성 > 하나의 객체를 유지시키는 코딩방법
                    mSelectedMarker = Marker()
                }
                mSelectedMarker!!.position = latLng
                mSelectedMarker!!.map = naverMap

//                클릭한 위치(latLng)로 카메라 이동 > 마커가 가운데 위치

                val cameraUpdate = CameraUpdate.scrollTo(latLng)
                naverMap.moveCamera(cameraUpdate)

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
