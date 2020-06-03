package com.android.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.cancel
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Created by Rahul khurana on 30/05/20.
 */
class MainActivity : AppCompatActivity()/*,CoroutineScope*/ {

/*
    override val coroutineContext:  CoroutineContext =  Dispatchers.Main + SupervisorJob()
*/

    lateinit var parentJob : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var handler = CoroutineExceptionHandler { _, exception ->

            print("Exception thrown in one of the children {$exception}")
        }
     parentJob = CoroutineScope(Dispatchers.IO).launch(handler){

      var firstJob =   launch {
        val result =   getResult(1)
          println("resultFirst"+result)
         }

         firstJob.invokeOnCompletion { throwable ->

             if(throwable !=null){
                 Log.d("firstJob..",throwable.localizedMessage)
             }
         }

         var secondJob =   launch {
             val result =   getResult(2)
             println("resultSecond"+result)

         }
        // delay(200)
        // secondJob.cancel(CancellationException("second job faulty"))

         secondJob.invokeOnCompletion { throwable ->

             if(throwable !=null){
                 Log.d("secondJob..",throwable.localizedMessage)
             }
         }

         var thirdJob =   launch {
           val result =  getResult(3)
             println("resultThird"+result)

         }

         thirdJob.invokeOnCompletion { throwable ->

             if(throwable !=null){
                 Log.d("thirdJob..",throwable.localizedMessage)
             }
         }

        }
        parentJob.invokeOnCompletion { throwable ->

            if(throwable !=null){
                Log.d("parentJob..",throwable.localizedMessage)
            }

        }
    }

    private suspend fun getResult(i: Long) : Long {
        delay(500*i)
        if(i==2L){
//           cancel(CancellationException("Exception in 2nd Job"))
        throw CancellationException("error in 2nd job")
        }

        return i;
    }

    private fun showResult() {
        Toast.makeText(this@MainActivity, "dcdc", Toast.LENGTH_SHORT).show()

    }


suspend fun getCall() = withContext(Dispatchers.IO){

   while(coroutineContext.isActive){
       Log.d("MainActivity","calling")
   }




}

    override fun onDestroy() {
        super.onDestroy()
       // coroutineContext.cancel()


    }


}