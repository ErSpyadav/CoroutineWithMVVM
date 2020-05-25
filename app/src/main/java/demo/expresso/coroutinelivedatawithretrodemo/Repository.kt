package demo.expresso.coroutinelivedatawithretrodemo

import androidx.lifecycle.LiveData
import demo.expresso.coroutinelivedatawithretrodemo.apis.MyRetrofitBuilder
import demo.expresso.coroutinelivedatawithretrodemo.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job :CompletableJob?=null

    //Exception handler
    val handler = CoroutineExceptionHandler{ _,exception ->
        println("Exception :"+exception.localizedMessage)

    }

    fun getUser(userId:String):LiveData<User>{
        job = Job()
        return object :LiveData<User>(){
            //ctr+o to override method
            override fun onActive() {
                super.onActive()
                job?.let {theJob ->
                    CoroutineScope(Dispatchers.Default + job!!).launch(handler){
                        val user = MyRetrofitBuilder.apiServer.getUser(userId)
                        println("Debug User: $user")
                      //  value=user//we cannot set livedata value in background thread
                        withContext(Dispatchers.Main){
                            value =user;
                            theJob.complete()
                        }

                    }

                }

            }
        }
    }

    fun clearJob(){
        job?.cancel()
    }
}
