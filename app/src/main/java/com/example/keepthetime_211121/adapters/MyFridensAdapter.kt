package com.example.keepthetime_211121.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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

        val data = mList[position]
        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = row.findViewById<TextView>(R.id.txtNickname)

        txtNickname.text = data.nickname
        Glide.with(mContext).load(data.profileImageURL).into(imgProfile)

        return row
    }
}