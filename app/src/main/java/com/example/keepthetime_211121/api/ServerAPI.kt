package com.example.keepthetime_211121.api

class ServerAPI {

//    통신을 담당하는 객체 - 레트로핏 객체 리턴 함수 작성.

    companion object{

//        서버 주소 > https 적용 주소 + 도메인
        private val BASE_URL = "https://keepthetime.xyz/"

//        Retrofit 형태의 변수 > OkHttpClient 처럼 실제 호출 담당
//        Retrofit 객체는 > 프로젝트를 통틀어 하나의 객체만 만들고 > 여러 화면에서 공유하자.
//        객체를 하나로만 유지하자 > 싱글턴 패턴 사용 ( 코드 디자인 패턴 )

        private var retrofit : Retrofit? = null // 처음엔 없다 > 하나만 만들고 이 변수를 공유.

        fun getRetrofit() : Retrofit {

            if (retrofit == null){

//                통신 담당 객체 아직 없으면 > 그때만 새로 만들자

//                통신 클라이언트 필요
                val myClient = OkHttpClient.Builder().build()  // 나중에 가공을 대비해서 builder로 만들자

//                 비어있는 retrofit 객체 생성

                retrofit = Retrofit.builder()
                    .baseUrl(BASE_URL)
                    .client(myClient)
                    .build()

            }

//            기존에 만든게 있다면, 그냥 그대로 리턴
            return retrofit!!

        }


    }


}