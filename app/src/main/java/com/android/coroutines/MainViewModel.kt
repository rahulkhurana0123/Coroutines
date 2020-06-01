package com.android.coroutines

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * Created by Rahul khurana on 01/06/20.
 */
class MainViewModel : ViewModel(){


    init {

        viewModelScope.launch {

                getCall()
                showResult()
        }


    }
    private fun showResult() {
        Log.d("result...","result")

    }
    suspend fun getCall() = withContext(Dispatchers.IO){

   while(isActive){
       Log.d("MainViewModel...","calling")
   }


        }


}