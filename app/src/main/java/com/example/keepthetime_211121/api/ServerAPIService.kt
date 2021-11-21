package com.example.keepthetime_211121.api

interface ServerAPIService {

//    기능별 주소(endpoint) / 메쏘드 (POST) / 파라미터 명시

    @POST("/user")
    fun postRequestLogin(
        @Field("email")  email: String,
        @Field("password")  pw: String
    )

}