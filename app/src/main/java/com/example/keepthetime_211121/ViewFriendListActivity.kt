package com.example.keepthetime_211121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_211121.adapters.MyFridensAdapter
import com.example.keepthetime_211121.databinding.ActivityViewFriendListBinding
import com.example.keepthetime_211121.datas.BasicResponse
import com.example.keepthetime_211121.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFriendListActivity : BaseActivity() {

    lateinit var binding : ActivityViewFriendListBinding

    val mMyFriendList = ArrayList<UserData>()

    lateinit var mFriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_friend_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getMyFriendsFromServer()

        mFriendAdapter = MyFridensAdapter(mContext,R.layout.my_friend_list_item,mMyFriendList)
        binding.myFriendListView.adapter = mFriendAdapter

    }

    fun getMyFriendsFromServer(){
        apiService.getRequestMyFriends("my").enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!

                    mMyFriendList.addAll(br.data.friends)

                    mFriendAdapter.notifyDataSetChanged()


                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}