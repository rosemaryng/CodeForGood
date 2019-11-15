/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*if (needInit()) {
            startActivity(
                Intent(
                    this, InitActivity::class.java
                )
            )
        } else if (showDaily()) {
            startActivity(
                Intent(
                    this, DailyActivity::class.java
                )
            )
        }*/

        val viewPager = findViewById<ViewPager>(R.id.view_pager).apply {
            adapter = MyMainViewPagerAdapter(supportFragmentManager, application)
            setOnTouchListener { _, _ -> true }
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav).apply {
            setOnNavigationItemSelectedListener {
                viewPager.currentItem = when (it.itemId) {
                    R.id.action_task -> 0
                    R.id.action_game -> 1
                    R.id.action_chat -> 2
                    else -> 3
                }

                true
            }

            itemIconTintList = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor = getPreferences(Context.MODE_PRIVATE).edit()
        editor.putLong(PREF_PREV_LOGIN, Date().time)
        editor.apply()
    }

    private fun needInit(): Boolean {
        val age: Int
        try {
            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
            age = sharedPref.getInt(PREF_AGE, 0)
        } catch (e: Resources.NotFoundException) {
            return true
        }
        return age <= 0
    }


    private fun showDaily(): Boolean {
//        return true
        val previousLogin: Long

        try {
            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
            previousLogin = sharedPref.getLong(PREF_PREV_LOGIN, Date().time)
        } catch (e: Resources.NotFoundException) {
            return true
        }

        return Date().time - previousLogin >= 86400000
    }

    private class MyMainViewPagerAdapter(fm: FragmentManager, val application: Application) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> TaskFragment()
                1 -> GameFragment(application)
                2 -> ChatFragment(application)
                else -> RoomFragment()
            }
        }

        override fun getCount() = 4
    }

    companion object {
        const val PREF_AGE = "PREF_AGE"
        const val PREF_PREV_LOGIN = "PREF_PREV_LOGIN"
        const val PREF_NAME = "PREF_NAME"
    }
}
