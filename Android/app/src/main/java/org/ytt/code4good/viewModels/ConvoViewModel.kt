/*
 * Created by Yip Tsz To on 11/2/19 8:29 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good.viewModels

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

data class ConvoViewModel(
    val app: Application,
    var name : String,
    @DrawableRes var imageSrc : Int,
    var message : String,
    var time : String
) : AndroidViewModel(app) {
    fun getDrawable() = ContextCompat.getDrawable(getApplication(), imageSrc)
}