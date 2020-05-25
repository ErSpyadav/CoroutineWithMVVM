package demo.expresso.coroutinelivedatawithretrodemo.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder  {

    const val BASE_URL ="https://open-api.xyz/"
    val retrofit :Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiServer:ApiServer by lazy {
        retrofit.build()
            .create(ApiServer::class.java)
    }


}
