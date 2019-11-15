/*
 * Created by Yip Tsz To on 11/2/19 5:01 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment(val event: OnForwardEvent) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_daily_welcome, container, false)
        var name = ""
        try {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            name = sharedPref?.getString(MainActivity.PREF_NAME, "") ?: ""
        } catch (e: Resources.NotFoundException) {
        }

        view.findViewById<TextView>(R.id.view_text).text = "Welcome Back,\n$name"
        view.findViewById<ImageView>(R.id.view_forward)
            .setOnClickListener { event.welcomeTouchListener() }

        return view
    }

    interface OnForwardEvent {
        fun welcomeTouchListener()
    }
}

class FeelingFragment(val event: OnForwardEvent) : Fragment() {
    private val listener = View.OnClickListener {
        val tag = it.tag
        event.feelingTouchListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_daily_feeling, container, false)

        val button0 = view.findViewById<Button>(R.id.view_feeling_0)
        val button1 = view.findViewById<Button>(R.id.view_feeling_1)
        val button2 = view.findViewById<Button>(R.id.view_feeling_2)
        val button3 = view.findViewById<Button>(R.id.view_feeling_3)

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)

        return view
    }

    interface OnForwardEvent {
        fun feelingTouchListener()
    }
}

class NewFriendFragment(event: OnForwardEvent) : Fragment() {
    private val listener = View.OnClickListener {
        val tag = it.tag.toString()
        if (tag == "0") {
            event.newFriendTouchListener()
        } else {
            event.newFriendExitListener()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_daily_new_friend, container, false)

        val button0 = view.findViewById<Button>(R.id.view_feeling_0)
        val button1 = view.findViewById<Button>(R.id.view_feeling_1)

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)

        return view
    }

    interface OnForwardEvent {
        fun newFriendTouchListener()

        fun newFriendExitListener()
    }
}

class WhoFragment(val event: OnForwardEvent) : Fragment() {
    private val listener = View.OnClickListener {
        val tag = it.tag
        event.whoTouchListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_daily_who, container, false)

        val button0 = view.findViewById<Button>(R.id.view_feeling_0)
        val button1 = view.findViewById<Button>(R.id.view_feeling_1)
        val button2 = view.findViewById<Button>(R.id.view_feeling_2)
        val button3 = view.findViewById<Button>(R.id.view_feeling_3)
        val button4 = view.findViewById<Button>(R.id.view_feeling_4)

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)

        return view
    }

    interface OnForwardEvent {
        fun whoTouchListener()
    }
}

class TopicFragment(val event: OnForwardEvent) : Fragment() {
    private val listener = View.OnClickListener {
        val tag = it.tag
        event.topicTouchListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_daily_topic, container, false)

        val button0 = view.findViewById<Button>(R.id.view_feeling_0)
        val button1 = view.findViewById<Button>(R.id.view_feeling_1)
        val button2 = view.findViewById<Button>(R.id.view_feeling_2)
        val button3 = view.findViewById<Button>(R.id.view_feeling_3)
        val button4 = view.findViewById<Button>(R.id.view_feeling_4)
        val button5 = view.findViewById<Button>(R.id.view_feeling_5)

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)

        return view
    }

    interface OnForwardEvent {
        fun topicTouchListener()
    }
}