package com.example.pppbapi;

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pppbapi.databinding.AgentListBinding
import com.example.pppbapi.model.Abilities
import com.example.pppbapi.model.AgentData;

typealias onClickAgent = (AgentData) -> Unit

class AgentsAdapter ( private val listAgentData: List<AgentData>,
                      private val listAbilities: List<Abilities>,
                      private val onClickAgent: onClickAgent)   :
    RecyclerView.Adapter<AgentsAdapter.AgentDataViewHolder>(){

    inner class AgentDataViewHolder(private val binding: AgentListBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: AgentData){
                    with(binding){
                        agentNameTxt.text = data.agentName.uppercase()
//                        agentRoleTxt.text = data.agentAbilities[0].abilityName
                        agentRoleTxt.text = data.agentRole.roleName
                        addAgentIcon(data.agentIcon)
                        addRoleIcon(data.agentRole.roleIcon)

                        Log.d("Cek Adapter","Agent Name ${agentNameTxt.text}")

                        itemView.setOnClickListener{
                            onClickAgent(data)
                        }
                    }
                }
                fun addAgentIcon(image: String){
                    Glide.with(itemView.context)
                        .load(image)
                        .into(binding.agentIconImg)
                }
                fun addRoleIcon(image: String){
                    Glide.with(itemView.context)
                        .load(image)
                        .into(binding.agentRoleImg)
                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentDataViewHolder {
        val binding =AgentListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return AgentDataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listAgentData.size
    }

    override fun onBindViewHolder(holder: AgentDataViewHolder, position: Int) {
        holder.bind(listAgentData[position])
    }
}
