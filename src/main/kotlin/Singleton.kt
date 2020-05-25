import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singleton {
    companion object{
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://api.kawalcorona.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
    }
}