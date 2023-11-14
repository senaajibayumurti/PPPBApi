package com.example.pppbapi

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pppbapi.databinding.ActivityMainBinding
import com.example.pppbapi.model.AgentData
import com.example.pppbapi.model.Agents
import com.example.pppbapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val listOfAgents = arrayListOf<AgentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val agentAdapter = AgentsAdapter(generateListOfAgents()) { agent ->
            // Handle on click event for the item
        }


        with(binding) {
            rvView.apply {
                adapter = agentAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

        val client = ApiClient.getInstance()
        val response = client.getAllAgents()
        response.enqueue(object : Callback<Agents> {
            override fun onResponse(call: Call<Agents>, response: Response<Agents>) {
                val agents = response.body()?.data ?: listOfAgents

                for (data in agents){
                    s
                }
//                listOfAgents.clear()
//                listOfAgents.addAll(agents)
//
//                agentAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Agents>, t: Throwable) {
                // TODO: Handle failure
            }
        })
    }

    private fun generateListOfAgents(): List<AgentData> {
        return listOfAgents
    }
}