package com.android.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Created by Rahul khurana on 01/06/20.
 */
class SecondViewModel : ViewModel() {

    init {


        viewModelScope.launch {


        }
    }
}