package com.example.keepthetime_211121.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.keepthetime_211121.R
import com.example.keepthetime_211121.datas.UserData

class MyFridensAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<UserData>
) : ArrayAdapter<UserData>(mContext,resId,mList){

    val  mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null){
            tempRow = mInflater.inflate(R.layout.my_friend_list_item,null)
        }
        val row = tempRow!!

        return row
    }
}