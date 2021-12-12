package com.example.keepthetime_211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityViewPlaceMapBinding
import com.example.keepthetime_211121.datas.ScheduleData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener

class ViewPlaceMapActivity : BaseActivity() {

    lateinit var binding : ActivityViewPlaceMapBinding

    lateinit var mScheduleData : ScheduleData

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
        mScheduleData = intent.getSerializableExtra("schedule") as ScheduleData

//        0. 프로젝트에 네이버지도 설치(완료)

//        1. 화면(xml)에 네이버 맵 띄워주기(완료)

//        2. 네이버맵 객체를 실제로 얻어내기 > getMapAsync

        binding.naverMapView.getMapAsync {

            val naverMap = it

//        3. 카메라 이동 / 마커 추가

//            위치(좌표) 데이터 객체

            val coord = LatLng( mScheduleData.latitude,mScheduleData.longitude )

            val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

            val marker = Marker()
            marker.position = coord
            marker.map = naverMap

//            추가기능 체험 - 정보창(말풍선) > 마커에 반영

            val infoWindow = InfoWindow()
            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(mContext){
                override fun getText(p0: InfoWindow): CharSequence {
                    return mScheduleData.place
                }

            }

            infoWindow.open(marker)

            naverMap.setOnMapClickListener { pointF, latLng ->
                infoWindow.close()
            }

            marker.setOnClickListener {
                if(marker.infoWindow ==null){
                    infoWindow.open(marker)
                }
                else{
//                    이미 정보창이 열린상태 > 닫아주기
                    infoWindow.close()
                }
                return@setOnClickListener true

            }

//            ODSay 라이브러리 재활용 > JSON 파싱 > 출발지~도착지 대중교통 경로 지도에 표시
//            복잡한 모양의 JSONObject 직접 파싱 복습

//            출발지 정보 ~ 도착지 API 호출
//            학원 or 집 좌표 출발지 지정

            val startingPoint = LatLng(37.56499045814495, 127.07210146981757)

//            오디세이 라이브러리로 > 대중교통 경로 API 호출

            val myODsayService = ODsayService.init(mContext, resources.getString(R.string.odsay_key))
            myODsayService.requestSearchPubTransPath(
                127.07210146981757.toString(),
                37.56499045814495.toString(),
                mScheduleData.longitude.toString(),
                mScheduleData.latitude.toString(),
                null,
                null,
                null,
                object : OnResultCallbackListener{
                    override fun onSuccess(p0: ODsayData?, p1: API?) {

                    }

                    override fun onError(p0: Int, p1: String?, p2: API?) {

                    }

                }
            )

            val endPoint = LatLng(mScheduleData.latitude,mScheduleData.longitude)



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