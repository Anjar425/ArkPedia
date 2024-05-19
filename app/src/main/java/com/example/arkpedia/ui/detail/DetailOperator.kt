package com.example.arkpedia.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.arkpedia.R
import com.example.arkpedia.data.parcelize.Base
import com.example.arkpedia.data.parcelize.Character
import com.example.arkpedia.data.parcelize.E0max
import com.example.arkpedia.data.parcelize.OperatorsData
import com.example.arkpedia.data.parcelize.VoiceLines
import com.example.arkpedia.ui.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailOperator : AppCompatActivity() {
    companion object {
        data class TabItem(val iconRes: Int, val textRes: Int)

        val TAB_ITEMS: Array<TabItem> = arrayOf(
            TabItem(R.drawable.operators_icon, R.string.tab_text_1),
            TabItem(R.drawable.files_icon, R.string.tab_text_2),
            TabItem(R.drawable.voicelines_icon, R.string.tab_text_3)
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_operator)



        val character = Character(
            intent.getStringExtra("gender"),
            intent.getStringExtra("place_of_birth"),
            intent.getStringExtra("birthday"),
            intent.getStringExtra("race"),
            intent.getStringExtra("height"),
            intent.getStringExtra("combat_experience"),
            intent.getStringExtra("infection_status"),
            intent.getStringExtra("physical_strength"),
            intent.getStringExtra("mobility"),
            intent.getStringExtra("physiological_endurance"),
            intent.getStringExtra("tactical_planning"),
            intent.getStringExtra("combat_skill"),
            intent.getStringExtra("originium_adaptability")
        )

        val descVoiceLines = arrayOf(
            intent.getStringExtra("appointed_as_assistant") ?: "",
            intent.getStringExtra("talk_1") ?: "",
            intent.getStringExtra("talk_2") ?: "",
            intent.getStringExtra("talk_3") ?: "",
            intent.getStringExtra("talk_after_promotion_1") ?: "",
            intent.getStringExtra("talk_after_promotion_2") ?: "",
            intent.getStringExtra("talk_after_trust_increase_1") ?: "",
            intent.getStringExtra("talk_after_trust_increase_2") ?: "",
            intent.getStringExtra("talk_after_trust_increase_3") ?: "",
            intent.getStringExtra("idle") ?: "",
            intent.getStringExtra("onboard") ?: "",
            intent.getStringExtra("watching_battle_record") ?: "",
            intent.getStringExtra("promotion_1") ?: "",
            intent.getStringExtra("promotion_2") ?: "",
            intent.getStringExtra("added_to_squad") ?: "",
            intent.getStringExtra("appointed_as_squad_leader") ?: "",
            intent.getStringExtra("depart") ?: "",
            intent.getStringExtra("begin_operation") ?: "",
            intent.getStringExtra("selecting_operator_1") ?: "",
            intent.getStringExtra("selecting_operator_2") ?: "",
            intent.getStringExtra("deployment_1") ?: "",
            intent.getStringExtra("deployment_2") ?: "",
            intent.getStringExtra("in_battle_1") ?: "",
            intent.getStringExtra("in_battle_2") ?: "",
            intent.getStringExtra("in_battle_3") ?: "",
            intent.getStringExtra("in_battle_4") ?: "",
            intent.getStringExtra("4-star_result") ?: "",
            intent.getStringExtra("3-star_result") ?: "",
            intent.getStringExtra("sub_3-star_result") ?: "",
            intent.getStringExtra("operation_failure") ?: "",
            intent.getStringExtra("assigned_to_facility") ?: "",
            intent.getStringExtra("tap") ?: "",
            intent.getStringExtra("trust_tap") ?: "",
            intent.getStringExtra("title") ?: "",
            intent.getStringExtra("greeting") ?: ""
        )
        val keyVoice = resources.getStringArray(R.array.voice_lines)
        val voiceLines = ArrayList<VoiceLines>()
        for (i in keyVoice.indices){
            val value = VoiceLines(keyVoice[i], descVoiceLines[i])
            voiceLines.add(value)
        }

        val base = Base(
            hp = intent.getStringExtra("base_hp") ?: "",
            atk = intent.getStringExtra("base_atk") ?: "",
            def = intent.getStringExtra("base_def") ?: "",
            resist = intent.getStringExtra("base_resist") ?: "",
            deploy = intent.getStringExtra("base_deploy") ?: "",
            cost = intent.getStringExtra("base_cost") ?: "",
            interval = intent.getStringExtra("base_interval") ?: "",
            block = intent.getStringExtra("base_block") ?: ""
        )

        val e0max = E0max(
            hp = intent.getStringExtra("e0max_hp") ?: "",
            atk = intent.getStringExtra("e0max_atk") ?: "",
            def = intent.getStringExtra("e0max_def") ?: "",
            block = intent.getStringExtra("e0max_block") ?: ""
        )



        val data = OperatorsData(
            intent.getStringExtra("name"),
            intent.getStringExtra("image"),
            intent.getStringExtra("profile"),
            intent.getStringExtra("className"),
            intent.getIntExtra("rarity", 0),
            character,
            voiceLines,
            base,
            e0max
            )


        val sectionsPagerAdapter = SectionPagerAdapter(this, data)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)


        TabLayoutMediator(tabs, viewPager) { tab, position ->
            val tabItem = TAB_ITEMS[position]
            tab.setIcon(tabItem.iconRes)
            tab.setText(tabItem.textRes)
        }.attach()


        enableEdgeToEdge()
        supportActionBar?.elevation = 0f


    }
}