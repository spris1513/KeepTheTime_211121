package com.example.keepthetime_211121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.api.ServerAPI
import com.example.keepthetime_211121.api.ServerAPIService
import com.example.keepthetime_211121.databinding.ActivityEditStartingPointBinding
import com.example.keepthetime_211121.datas.BasicResponse
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditStartingPointActivity : BaseActivity() {

    lateinit var binding: ActivityEditStartingPointBinding

    var mSelectedLatLng: LatLng? = null
    var mSelectedMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_starting_point)
        binding.naverMapView.onCreate(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        binding.btnSaveStartingPoint.setOnClickListener {

            val inputName = binding.edtInputPlace.text.toString()

//            장소 이름이 2자 이상이어야 진행 되도록
            if(inputName.length < 2 ){

                Toast.makeText(mContext, "장소이름은 2자 이상으로 해주세요", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

//            지도에서 위치를 선택 했어야 진행되도록
//            mSelectedLatLng가 지금 시점에도 null이면 > 지도에서 위치선택 안하고 저장버튼 눌렀다.
            if (mSelectedLatLng == null){
                Toast.makeText(mContext, "지도에서 장소를 선택 해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            apiService.postRequestStartingPoint(
                inputName,
                mSelectedLatLng!!.latitude,
                mSelectedLatLng!!.longitude,
                true
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
//                    응답이 최종 성공 > 장소등록 성공 토스트 / 화면종료
                    if(response.isSuccessful){
                        Toast.makeText(mContext, "출발 장소 등록에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(mContext, "출발 장소 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })


        }


    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {

            val naverMap = it

//            지도의 한 곳을 클릭하면 > 마커를 추가


            naverMap.setOnMapClickListener { pointF, latLng ->

//                클랙 될 때 마다 생성자 호출 > 매번 새 마커를 그려주고있다.
//                단 하나의 마커만 유지 > 아직 안그려졌을때만 생성하자
//                위치는 매번 클릭될 때 마다 설정 ( EditAppointmentActivity 참고)

                if (mSelectedMarker == null) {
//                    멤버변수로 만들어둔 마커가 null일때만 생성 > 하나의 객체를 유지시키는 코딩방법
                    mSelectedMarker = Marker()
                }
                mSelectedMarker!!.position = latLng
                mSelectedMarker!!.map = naverMap

//                클릭한 위치(latLng)로 카메라 이동 > 마커가 가운데 위치

                val cameraUpdate = CameraUpdate.scrollTo(latLng)
                naverMap.moveCamera(cameraUpdate)

//                선택된 위치를 서버에 보낼 때 활용 , 멤버변수에 선택된 위치를 저장
                mSelectedLatLng = latLng

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
