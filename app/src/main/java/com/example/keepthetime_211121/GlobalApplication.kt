package com.example.keepthetime_211121

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this,"6e29c8023ab377380cb8efb6bcb5ec8c")
    }


}