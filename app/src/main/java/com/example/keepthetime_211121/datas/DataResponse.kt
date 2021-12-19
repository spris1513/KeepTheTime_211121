package com.example.keepthetime_211121.datas

class DataResponse(
    var user : UserData,
    var token : String,

//    친구목록 API 파싱에만 사용
    var friends : List<UserData>,

    var users : List<UserData>,

//    약속 목록 API 파싱에서 활용.
    var appointments : List<ScheduleData>,

//    출발지 목록 API 파싱에서 활용
    var places : List<PlaceData>

) {
}