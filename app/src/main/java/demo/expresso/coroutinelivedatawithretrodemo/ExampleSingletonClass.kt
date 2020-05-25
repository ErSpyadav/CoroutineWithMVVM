package demo.expresso.coroutinelivedatawithretrodemo

import demo.expresso.coroutinelivedatawithretrodemo.models.User

object ExampleSingletonClass {

    //initialize when it call first time
    val exampleSingletonUser :User by lazy {
        User("sarayu264@gmail.com","Sarayu","Some")
    }
}