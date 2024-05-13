package com.example.arkpedia.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.arkpedia.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var fragmentHome = FragmentHome()
    private var fragmentList = FragmentList()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    openFragment(fragmentHome)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_list -> {
                    openFragment(fragmentList)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    openFragment(fragmentHome)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun openFragment (fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        openFragment(fragmentHome)
    }
}
