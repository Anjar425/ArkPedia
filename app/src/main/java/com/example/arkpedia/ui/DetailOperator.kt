package com.example.arkpedia.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.arkpedia.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailOperator : AppCompatActivity() {
    companion object {
        data class TabItem(val iconRes: Int, val textRes: Int)

        val TAB_ITEMS: Array<TabItem> = arrayOf(
            TabItem(R.drawable.icon1, R.string.tab_text_1),
            TabItem(R.drawable.icon2, R.string.tab_text_2),
            TabItem(R.drawable.icon1, R.string.tab_text_3)
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


        val data = OperatorsData(
            intent.getStringExtra("name"),
            intent.getStringExtra("image"),
            character
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

        val name = intent.getStringExtra("name")
        Log.w(name, "Error")

//        val fragmentStat = sectionsPagerAdapter.getFragmentInstance(0) as FragmentStat
//
//        fragmentStat.setData(name)
        supportActionBar?.elevation = 0f


    }
}