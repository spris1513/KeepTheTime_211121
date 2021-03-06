package com.example.keepthetime_211121

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.databinding.ActivityLoginBinding
import com.example.keepthetime_211121.datas.BasicResponse
import com.example.keepthetime_211121.utils.ContextUtil
import com.example.keepthetime_211121.utils.GlobalData
import com.facebook.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity() {

    lateinit var binding : ActivityLoginBinding

//    페북로그인에 다녀오면 할 일을 관리해주는 변수
    lateinit var callbackManager : CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setupEvents()
        setValues()
    }

    fun getKakaoUserInfo(token:OAuthToken){

//        실제로 내 정보를 요청하는 함수
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("카톡로그인", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i("카톡로그인", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

//               앱 서버에 카톡 로그인 API 호출
                val uid = user.id
                val nickname = user.kakaoAccount?.profile?.nickname!!

                apiService.postRequestSocialLogin("kakao", uid.toString(), nickname).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){
                            val br = response.body()!!
                            Log.d("앱서버토큰",br.data.token)
                            ContextUtil.setToken(mContext,br.data.token)

//                            로그인 한 사람 GlobalData
                            GlobalData.loginUser = br.data.user

                            val myIntent = Intent(mContext,MainActivity::class.java)
                            startActivity(myIntent)
                            finish()

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }


                })

            }
       }

    }

    override fun setupEvents() {

        binding.btnKakaotalkLogin.setOnClickListener {

//            카카오 로그인처리

//            카톡 앱이 깔려 있는지?
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)){

//                true 카톡앱이 설치됨
                UserApiClient.instance.loginWithKakaoTalk(mContext){ token, error ->

//                    AccessToken > Token : 로그인 한 삳용자의 고유 카톡 토큰값 > 본인 정보 요청
                    if (error != null){
                        Toast.makeText(mContext, "카톡 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        return@loginWithKakaoTalk
                    }
                    getKakaoUserInfo(token!!)
                }

            }
            else{

//                카톡 앱 미설치 > 카카오계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(mContext){token, error ->
                    if (error != null){
                        Toast.makeText(mContext, "카톡 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        return@loginWithKakaoAccount
                    }
                    getKakaoUserInfo(token!!)

                }

            }

        }

        binding.btnFacebookLogin.setOnClickListener {

//            소셜 로그인 로직 체험

//            페북로그인에 성공하게 되면 할 일도 미리 설정.

            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {

//                    페북로그인 성공 > 그 결과로 토큰 받아오기 성공

                    Log.d("로그인성공",AccessToken.getCurrentAccessToken().toString())

//                    이 토큰을 이용해서 > 페북에서 사용자 정보도 받아오자. > 페북전용 클래스 : GraphRequest

                    val graphRequest = GraphRequest.newMeRequest(result?.accessToken, object : GraphRequest.GraphJSONObjectCallback{
                        override fun onCompleted(jsonObj: JSONObject?, response: GraphResponse?) {
                            Log.d("내정보",jsonObj.toString())

                            val uid = jsonObj!!.getString("id")
                            val name = jsonObj.getString("name")

//                            페북 로그인에서 받아온 내 정보 > 앱 서버 소셜로그인 기능 호출
                            apiService.postRequestSocialLogin("facebook",uid,name).enqueue(object :Callback<BasicResponse>{
                                override fun onResponse(
                                    call: Call<BasicResponse>,
                                    response: Response<BasicResponse>
                                ) {
                                    if(response.isSuccessful){
                                        val br = response.body()!!
                                        Log.d("앱서버토큰",br.data.token)
                                        ContextUtil.setToken(mContext,br.data.token)

                                        //                            로그인 한 사람 GlobalData
                                        GlobalData.loginUser = br.data.user


                                        val myIntent = Intent(mContext,MainActivity::class.java)
                                        startActivity(myIntent)
                                        finish()

                                    }



                                }

                                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                                }


                            })

                        }

                    })

//                    내 정보 받아오기 실행
                    graphRequest.executeAsync()

                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {

                }

            })

            LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"))


        }

        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogin.setOnClickListener {

//            입력한 email / pw 변수 담자
            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

//            2. 서버에 로그인 API 호출 > retrofit 사용

            apiService.postRequestLogin(inputEmail, inputPassword).enqueue( object :Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

//                    최종 성공 or 실패 여부에 따라 별도 코딩이 필요
                    if( response.isSuccessful) {
                        val basicResponse = response.body()!!

                        ContextUtil.setToken(mContext,basicResponse.data.token)

                        //                            로그인 한 사람 GlobalData
                        GlobalData.loginUser = basicResponse.data.user

                        Toast.makeText(mContext, "${basicResponse.data.user.nickname}님 환영합니다!", Toast.LENGTH_SHORT).show()

//                        메인화면 이동
                        val myIntent = Intent(mContext,MainActivity::class.java)
                        startActivity(myIntent)

                        finish() // 로그인화면 종료

                    }
                    else {
//                        로그인 결과 실패. > 실패 사유 (message) 토스트
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)

                        val message = jsonObj.getString("message")
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Toast.makeText(mContext, "서버 연결에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

            } )


        }

    }

    override fun setValues() {
        getKeyHash()

//        페북 로그인 - 콜백 관리 기능 생성
        callbackManager = CallbackManager.Factory.create()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getKeyHash(){
        val info = packageManager.getPackageInfo(
            "com.example.keepthetime_211121",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }

    }
}