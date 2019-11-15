/*
 * Created by Yip Tsz To on 11/2/19 8:10 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RoomFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_room, container, false)
    }
}