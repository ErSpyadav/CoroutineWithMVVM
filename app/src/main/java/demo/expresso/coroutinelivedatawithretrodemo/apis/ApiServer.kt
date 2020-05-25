package demo.expresso.coroutinelivedatawithretrodemo.apis

import demo.expresso.coroutinelivedatawithretrodemo.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServer {

    @GET("placeholder/user/{userId}")
    suspend fun getUser(@Path("userId") userId: String):User

    /*
    https://open-api.xyz/placeholder/user/1

email	"mitchelltabian@gmail.com"
username	"mitch"
image	"https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
    */
}