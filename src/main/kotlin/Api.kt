import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("sembuh")
    fun getGlobalSembuh():Call<Global_sembuh>

    @GET("meninggal")
    fun getGlobalMeninggal():Call<Global_meninggal>

    @GET("positif")
    fun getGlobalPositif():Call<Global_positif>
}