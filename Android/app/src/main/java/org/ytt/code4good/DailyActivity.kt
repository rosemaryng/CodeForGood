/*
 * Created by Yip Tsz To on 11/2/19 4:38 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class DailyActivity : AppCompatActivity(), WelcomeFragment.OnForwardEvent,
    FeelingFragment.OnForwardEvent, NewFriendFragment.OnForwardEvent, WhoFragment.OnForwardEvent,
    TopicFragment.OnForwardEvent {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        viewPager = findViewById<ViewPager>(R.id.view_pager_daily).apply {
            adapter = DailyViewPagerAdapter(
                supportFragmentManager,
                this@DailyActivity,
                this@DailyActivity,
                this@DailyActivity,
                this@DailyActivity,
                this@DailyActivity
            )
            setOnTouchListener { _, _ -> true }
        }
    }

    private class DailyViewPagerAdapter(
        fm: FragmentManager,
        val welcomeEvent: WelcomeFragment.OnForwardEvent,
        val feelingEvent: FeelingFragment.OnForwardEvent,
        val newFriendEvent: NewFriendFragment.OnForwardEvent,
        val whoEvent: WhoFragment.OnForwardEvent,
        val topicEvent: TopicFragment.OnForwardEvent
    ) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> WelcomeFragment(welcomeEvent)
                1 -> FeelingFragment(feelingEvent)
                2 -> NewFriendFragment(newFriendEvent)
                3 -> WhoFragment(whoEvent)
                else -> TopicFragment(topicEvent)
            }
        }

        override fun getCount() = 5
    }

    override fun welcomeTouchListener() {
        viewPager.currentItem++
    }

    override fun feelingTouchListener() {
        viewPager.currentItem++
    }

    override fun newFriendTouchListener() {
        viewPager.currentItem++
    }

    override fun newFriendExitListener() {
        finish()
    }

    override fun whoTouchListener() {
        viewPager.currentItem++
    }

    override fun topicTouchListener() {
        finish() // TODO ?
    }
}