package com.example.pppbapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pppbapi.databinding.AgentDetailsBinding

class AgentDetails : AppCompatActivity() {
    val binding by lazy {
        AgentDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val agentName =intent.getStringExtra("agentName")
        val agentDesc =intent.getStringExtra("agentDesc")
        val agentBackground =intent.getStringExtra("agentBackground")
        val agentImage =intent.getStringExtra("agentPortrait")
        val agentRoleName =intent.getStringExtra("agentRoleName")

        val abIcons = mutableListOf<String>()
        val abNames = mutableListOf<String>()
        val abDescs = mutableListOf<String>()
        for (i in 1 until 5) {
            val abIcon = intent.getStringExtra("agentAbilitiesIcon_$i")
            val abName = intent.getStringExtra("agentAbilitiesName_$i")
            val abDesc = intent.getStringExtra("agentAbilitiesDesc_$i")
            if (abIcon != null && abName != null && abDesc != null) {
                abIcons.add(abIcon)
                abNames.add(abName)
                abDescs.add(abDesc)
            }
        }

        fun updateAbilityDescription(abilityDescription: String, abilityName: String) {
            binding.tvAbilitiesDesc.text = abilityDescription
            binding.tvAbilitiesName.text = abilityName
        }
        binding.abilityIcon1.setOnClickListener {
            updateAbilityDescription(abDescs[0], abNames[0])
        }
        binding.abilityIcon2.setOnClickListener {
            updateAbilityDescription(abDescs[1], abNames[1])
        }
        binding.abilityIcon3.setOnClickListener {
            updateAbilityDescription(abDescs[2], abNames[2])
        }
        binding.abilityIcon4.setOnClickListener {
            updateAbilityDescription(abDescs[3], abNames[3])
        }


        with(binding){
            tvAgentName.text = agentName!!.uppercase()
            tvAgentRole.text = agentRoleName
            tvAbilitiesDesc.text = agentDesc

            Glide.with(this@AgentDetails).load(agentBackground).into(ivAgentBg)
            Glide.with(this@AgentDetails).load(agentImage).into(ivAgentImg)

            for (index in 0 until 4) {
                val abilityIconId = when (index) {
                    0 -> binding.abilityIcon1
                    1 -> binding.abilityIcon2
                    2 -> binding.abilityIcon3
                    3 -> binding.abilityIcon4
                    else -> throw IndexOutOfBoundsException()
                }

                Glide.with(this@AgentDetails).load(abIcons[index]).into(abilityIconId)
            }

        }
    }
}