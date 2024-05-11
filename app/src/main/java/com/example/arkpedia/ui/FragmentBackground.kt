package com.example.arkpedia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.arkpedia.R


private const val ARG_DATA = "param1"

class FragmentBackground : Fragment() {
    // TODO: Rename and change types of parameters
    private var data: OperatorsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_background, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genderLayout = view.findViewById<TextView>(R.id.gender)
        val placeOfBirthLayout = view.findViewById<TextView>(R.id.place_of_birth)
        val birthdayLayout = view.findViewById<TextView>(R.id.birthday)
        val raceLayout = view.findViewById<TextView>(R.id.race)
        val heightLayout = view.findViewById<TextView>(R.id.height)
        val combatExperienceLayout = view.findViewById<TextView>(R.id.combat_experience)
        val infectionStatusLayout = view.findViewById<TextView>(R.id.infection_status)
        val physicalStrengthLayout = view.findViewById<TextView>(R.id.physical_strength)
        val mobilityLayout = view.findViewById<TextView>(R.id.mobility)
        val physiologicalEnduranceLayout = view.findViewById<TextView>(R.id.physiological_endurance)
        val tacticalPlanningLayout = view.findViewById<TextView>(R.id.tactical_planning)
        val combatSkillLayout = view.findViewById<TextView>(R.id.combat_skill)
        val originiumAdaptabilityLayout = view.findViewById<TextView>(R.id.originium_adaptability)

        genderLayout.text = "Gender: " + data?.lore?.gender
        placeOfBirthLayout.text = "Place of Birth: " + data?.lore?.placeOfBirth
        birthdayLayout.text = "Birthday: " + data?.lore?.birthday
        raceLayout.text = "Race: " + data?.lore?.race
        heightLayout.text = "Height: " + data?.lore?.height
        combatExperienceLayout.text = "Combat Experience: " + data?.lore?.combatExperience
        infectionStatusLayout.text = "Infection Status: " + data?.lore?.infectionStatus
        physicalStrengthLayout.text = "Physical Strength: " + data?.lore?.physicalStrength
        mobilityLayout.text = "Mobility: " + data?.lore?.mobility
        physiologicalEnduranceLayout.text = "Physiological Endurance: " + data?.lore?.physiologicalEndurance
        tacticalPlanningLayout.text = "Tactical Planning: " + data?.lore?.tacticalPlanning
        combatSkillLayout.text = "Combat Skill: " + data?.lore?.combatSkill
        originiumAdaptabilityLayout.text = "Originium Adaptability: " + data?.lore?.originiumAdaptability



    }

    companion object {
        @JvmStatic
        fun newInstance(data: OperatorsData) =
            FragmentBackground().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA, data)
                }
            }
    }
}