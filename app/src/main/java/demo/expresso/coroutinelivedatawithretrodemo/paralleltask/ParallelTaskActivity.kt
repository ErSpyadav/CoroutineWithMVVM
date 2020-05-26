package demo.expresso.coroutinelivedatawithretrodemo.paralleltask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import demo.expresso.coroutinelivedatawithretrodemo.R
import kotlinx.android.synthetic.main.activity_parallel_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class ParallelTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel_task)
        clickMe.setOnClickListener {
          setNewText("Click Me!")
            fakeApiRequest()
        }


    }

    //first Way
    private fun fakeApiRequest(){
        val startTime =System.currentTimeMillis()
       val parentJob =CoroutineScope(IO).launch {
           val job1 = launch {
               val time1 = measureTimeMillis {
                   println("Debug : launching thread-${Thread.currentThread().name}")
                 val result1 = getResultFromApi1()
                   setTextOnMainThread("Got : $result1")
               }
               println("Debug: Job1 completed time : $time1")//2013
           }

           val job2 = launch {
               val time2 = measureTimeMillis {
                   println("Debug : launching thread-${Thread.currentThread().name}")
                   val result2 = getResultFromApi2()
                       setTextOnMainThread("Got : $result2")
               }
               println("Debug : Job2 completed time : $time2")//3006
           }
       }
        parentJob.invokeOnCompletion {
            println("Debug - Total time taken : ${System.currentTimeMillis() - startTime}")//3006
        }

    }

    //Other Way using Asyn and await builder
    private fun fakeApiRequest2(){
        CoroutineScope(IO).launch {
            val job1 = launch {
                val time1 = measureTimeMillis {
                    println("Debug : launching thread-${Thread.currentThread().name}")
                    val result1 = getResultFromApi1()
                    setTextOnMainThread("Got : $result1")
                }
                println("Debug: Job1 completed time : $time1")//2013
            }

            val job2 = launch {
                val time2 = measureTimeMillis {
                    println("Debug : launching thread-${Thread.currentThread().name}")
                    val result2 = getResultFromApi2()
                    setTextOnMainThread("Got : $result2")
                }
                println("Debug : Job2 completed time : $time2")//3006
            }
        }

    }



    private fun setNewText(input :String){
        val result=resultText.text.toString()+"\n$input"
        resultText.text=result
    }

    private suspend fun setTextOnMainThread(input :String){
        withContext(Main){
            setNewText(input)
        }
    }
    private suspend fun getResultFromApi1():String{
        delay(2000)
        return "#1"
    }
    private suspend fun getResultFromApi2():String{
        delay(3000)
        return "#2"
    }

}
