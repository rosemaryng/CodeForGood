/*
 * Created by Yip Tsz To on 11/2/19 4:18 AM.
 * Copyright (c) 2019.
 */

package org.ytt.code4good.viewModels

import androidx.lifecycle.ViewModel

data class TaskViewModel(
    var name: String,
    var coins: String
) : ViewModel()