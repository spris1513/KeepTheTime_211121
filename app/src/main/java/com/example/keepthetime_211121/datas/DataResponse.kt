package com.example.keepthetime_211121.datas

class DataResponse(
    var user : UserData,
    var token : String,

//    친구목록 API 파싱에만 사용
    var friends : List<UserData>
) {
}