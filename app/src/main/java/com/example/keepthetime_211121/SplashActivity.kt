package com.example.keepthetime_211121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.keepthetime_211121.datas.BasicResponse
import com.example.keepthetime_211121.utils.ContextUtil
import com.example.keepthetime_211121.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        2초가 지나면 어느 화면으로 가야할 지 (메인 Vs. 로그인) 결정 > 이동

        val myHandler = Handler(Looper.getMainLooper())  // UI 담당 쓰레드를 불러내는 역할

        myHandler.postDelayed( {

             val myIntent : Intent

//             자동 로그인 할 상황인지 검사. >> 내가 갖고 있는 토큰이, 유효한 토큰인가?
//             내 정보 API를 통해서 > 사용자 정보가 저장되었는가?
             if(GlobalData.loginUser == null){

//                 토큰이 없어나 or 있지만 유효하지 않으면 >> 로그인 진행 필요
                 myIntent = Intent(mContext,LoginActivity::class.java)
             }
            else{
//                자동로그인 해도 됨
                myIntent = Intent(mContext,MainActivity::class.java)
             }
            startActivity(myIntent)
            finish()

        },2000 )

    }

    override fun setValues() {
        testToken()

    }

    fun testToken() {

//        ContextUtil에 저장한 토큰이 유효한 토큰인지 검사 > 내 정보 불러오기. 잘 불러오는지 확인.
        apiService.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
//                    내 정보 불러오기 성공
                    val br = response.body()!!

//                    GlobalData 의 로그인 사용자에 사용자 정보를 저장
                    GlobalData.loginUser = br.data.user


                }
                else{
//                    내 정보 불러오기 실패
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

    }

}