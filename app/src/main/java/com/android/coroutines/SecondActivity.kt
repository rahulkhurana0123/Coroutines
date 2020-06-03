package com.android.coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

/**
 * Created by Rahul khurana on 01/06/20.
 */
class SecondActivity : AppCompatActivity(){

    lateinit var parentJob : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /// coroutines can be started from normal function
        // scope.launch
        parentJob =   CoroutineScope(Dispatchers.IO).launch{

           getData() // suspend the coroutines
           showData() // resume

       }


    }

    private fun showData() {

    }


    // suspend function can be called from suspend function or from coroutines
    // suspend function cant be call from normal function
    suspend fun getData() = withContext(Dispatchers.IO){

        while(isActive){
            Log.d("SecondActivity","i am in coroutine")
        }
     //   delay(1000)  // io thread
        // it will give back the control to coroutines
    }

    override fun onDestroy() {
        super.onDestroy()


      parentJob.cancel()
    }
}