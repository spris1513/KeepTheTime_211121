package com.example.keepthetime_211121.utils

import com.example.keepthetime_211121.datas.UserData

class GlobalData {

    companion object{

//        앱이 켜지면 로그인한 사용자가 없다고 기본값 세팅.
        var loginUser : UserData? = null

    }

}