package com.example.pppbapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pppbapi.databinding.ActivityMainBinding
import com.example.pppbapi.model.Abilities
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
    val listOfAbilities = arrayListOf<Abilities>()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val agentAdapter = AgentsAdapter(listOfAgents, listOfAbilities) { agent ->
            Toast.makeText(this@MainActivity, "${agent.agentName.uppercase()}", Toast.LENGTH_SHORT).show()
            val IntentToAgentDetails = Intent(this@MainActivity, AgentDetails::class.java)

            IntentToAgentDetails.putExtra("agentName", agent.agentName)
            IntentToAgentDetails.putExtra("agentDesc", agent.agentDesc)
            IntentToAgentDetails.putExtra("agentBackground", agent.agentBackground)
            IntentToAgentDetails.putExtra("agentPortrait", agent.agentImage)
            IntentToAgentDetails.putExtra("agentRoleName", agent.agentRole.roleName)
            for (i in 0 until agent.agentAbilities.size) {
                IntentToAgentDetails.putExtra("agentAbilitiesIcon_${i + 1}", getAgentAbilityIcon(i, agent.agentAbilities))
            }
            for (i in 0 until agent.agentAbilities.size) {
                IntentToAgentDetails.putExtra("agentAbilitiesName_${i + 1}", getAgentAbilityName(i, agent.agentAbilities))
            }
            for (i in 0 until agent.agentAbilities.size) {
                IntentToAgentDetails.putExtra("agentAbilitiesDesc_${i + 1}", getAgentAbilityDesc(i, agent.agentAbilities))
            }

            startActivity(IntentToAgentDetails)
        }


        val client = ApiClient.getInstance()
        val response = client.getAllAgents()
        response.enqueue(object : Callback<Agents> {
            override fun onResponse(call: Call<Agents>, response: Response<Agents>) {
                val agents = response.body()?.data ?: listOf()

                listOfAgents.clear()
                agents.forEach {
                if(it.uuid != "ded3520f-4264-bfed-162d-b080e2abccf9")
                    listOfAgents.add(it)
                }
                agentAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Agents>, t: Throwable) {
            }
        })

        with(binding) {
            rvView.apply {
                adapter = agentAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun getAgentAbilityName(index: Int, agentAbilities: List<Abilities>): String {
        return agentAbilities[index].abilityName
    }
    private fun getAgentAbilityDesc(index: Int, agentAbilities: List<Abilities>): String {
        return agentAbilities[index].abilityDesc
    }
    fun getAgentAbilityIcon(index: Int, agentAbilities: List<Abilities>): String {
        return agentAbilities[index].abilityIcon
    }
}