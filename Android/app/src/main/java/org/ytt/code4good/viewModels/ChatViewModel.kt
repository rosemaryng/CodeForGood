/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */


package org.ytt.code4good.viewModels

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

data class ChatViewModel(
    val app: Application,
    @DrawableRes var imageSrc: Int,
    var name: String,
    var streak: Int,
    var detail: String,
    var time: String
) : AndroidViewModel(app) {

    /*companion object {
        val convo = listOf(
            ConvoViewModel(app, "", "Hey, how was your day?", "4:53pm"),

            ConvoViewModel(app, "Lisa", "Just finished my treatment today, not so great", "4:55pm"),
            ConvoViewModel(app, "Lisa", "Spent hours doing nothing already...", "4:56pm"),

            ConvoViewModel(app, "", "Don't think about it, you'll get better very soon!", "4:57pm"),
            ConvoViewModel(app, "", "I went through that last week, it wasn't great.", "4:58pm"),
            ConvoViewModel(app,
                "",
                "But Peter from the ward next door invited me to try this new game, want to check it out?.",
                "4:58pm"
            ),

            ConvoViewModel(app, "Lisa", "Not sure if I'm awake enough for anything intensive", "5:00pm"),

            ConvoViewModel(app, "", "It's the new category game, very simple and fun!", "5:01pm"),

            ConvoViewModel(app, "Lisa", "Sure then!", "5:02pm")
        )
    }*/

    fun getDrawable() = ContextCompat.getDrawable(getApplication(), imageSrc)
}