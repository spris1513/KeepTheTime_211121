package com.example.keepthetime_211121.datas

import com.google.gson.annotations.SerializedName

class PlaceData(

    var id : Int,
    @SerializedName("name")
    var placeName : String,
    var latitude : Double,
    var longitude : Double,

) {
}