package com.example.arkpedia.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.arkpedia.data.parcelize.OperatorsData
import com.example.arkpedia.ui.detail.FragmentFile
import com.example.arkpedia.ui.detail.FragmentStat
import com.example.arkpedia.ui.detail.FragmentVoicelines

class SectionPagerAdapter(activity: AppCompatActivity, private val data: OperatorsData) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentStat.newInstance(data)
            1 -> fragment = FragmentFile.newInstance(data)
            2 -> fragment = FragmentVoicelines.newInstance(data)
        }
        return fragment as Fragment

    }
}