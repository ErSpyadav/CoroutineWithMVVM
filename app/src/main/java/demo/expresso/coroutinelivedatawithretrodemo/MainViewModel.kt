package demo.expresso.coroutinelivedatawithretrodemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import demo.expresso.coroutinelivedatawithretrodemo.models.User

class MainViewModel :ViewModel(){

    val _userId :MutableLiveData<String> = MutableLiveData()

    val user :LiveData<User> =Transformations
        .switchMap(_userId){ userid ->
            Repository.getUser(userid)
        }
    /*When _userId value will change,switchMap method will trigger*/

    fun setUserId(userId:String){
        val update =userId
        if(_userId.value == update)
            return
        else{
            _userId.value=update
        }
    }

    fun cancelJob(){
        Repository.clearJob()
    }
}