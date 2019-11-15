/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good.viewModels

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel

data class GameViewModel(
    val context: Application,
    var name: String,
    @DrawableRes private var drawable: Int,
    var category: String
) : AndroidViewModel(context) {
    fun getDrawable() = ContextCompat.getDrawable(getApplication(), drawable)
}