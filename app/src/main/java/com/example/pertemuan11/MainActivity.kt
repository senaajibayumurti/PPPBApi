package com.example.pertemuan11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.pertemuan11.databinding.ActivityMainBinding
import com.example.pertemuan11.model.Users
import com.example.pertemuan11.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getAllUser()
        response.enqueue(object : retrofit2.Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val namaUsers = ArrayList<String>()
                for (i in response.body()?.data ?: arrayListOf()){
                    namaUsers.add(i.employeeName)
                }
                val listAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    namaUsers
                )
                binding.lsView.adapter = listAdapter
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}