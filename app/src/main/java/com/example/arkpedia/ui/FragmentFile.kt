package com.example.arkpedia.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.arkpedia.R


private const val ARG_DATA = "param1"

class FragmentFile : Fragment() {
    private var data: OperatorsData? = null

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            data = bundle.getParcelable(ARG_DATA)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val codeNameLayout = view.findViewById<TextView>(R.id.code_name)
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
        val profileLayout = view.findViewById<TextView>(R.id.profile)

        codeNameLayout.text = getString(R.string.code_name_layout_text, data?.name)
        genderLayout.text = getString(R.string.gender_layout_text, data?.lore?.gender)
        placeOfBirthLayout.text = getString(R.string.place_of_birth_layout_text, data?.lore?.placeOfBirth)
        birthdayLayout.text = getString(R.string.birthday_layout_text, data?.lore?.birthday)
        raceLayout.text = getString(R.string.race_layout_text, data?.lore?.race)
        heightLayout.text = getString(R.string.height_layout_text, data?.lore?.height)
        combatExperienceLayout.text = getString(R.string.combat_experience_layout_text, data?.lore?.combatExperience)
        infectionStatusLayout.text = getString(R.string.infection_status_layout_text, data?.lore?.infectionStatus)
        physicalStrengthLayout.text = getString(R.string.physical_strength_layout_text, data?.lore?.physicalStrength ?: "Unknown")
        mobilityLayout.text = getString(R.string.mobility_layout_text, data?.lore?.mobility ?: "Unknown")
        physiologicalEnduranceLayout.text = getString(R.string.physiological_endurance_layout_text, data?.lore?.physiologicalEndurance ?: "Unknown")
        tacticalPlanningLayout.text = getString(R.string.tactical_planning_layout_text, data?.lore?.tacticalPlanning ?: "Unknown")
        combatSkillLayout.text = getString(R.string.combat_skill_layout_text, data?.lore?.combatSkill ?: "Unknown")
        originiumAdaptabilityLayout.text = getString(R.string.originium_adaptability_layout_text, data?.lore?.originiumAdaptability ?: "Unknown")
        profileLayout.text = getString(R.string.profile_layout_text, data?.profile)

    }

    companion object {
        @JvmStatic
        fun newInstance(data: OperatorsData) =
            FragmentFile().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA, data)
                }
            }
    }
}