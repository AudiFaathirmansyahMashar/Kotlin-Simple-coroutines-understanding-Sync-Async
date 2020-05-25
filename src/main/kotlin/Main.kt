import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val timeTwo = measureTimeMillis {
        val sembuh = async { getSembuh() }
        val meninggal = async { getMeninggal() }
        val positif = async { getPositif() }
        println("${sembuh.await()}\n${meninggal.await()}\n${positif.await()}")
    }

    println("===================================")

    val timeOne = measureTimeMillis {
        val sembuh = getSembuh()
        val meninggal = getMeninggal()
        val positif = getPositif()
        println("$sembuh\n$meninggal\n$positif")
    }

    println("Completed in $timeOne ms vs $timeTwo ms")

}

private var sembuh:String = "0"
suspend fun getSembuh(): String{
    val call:Call<Global_sembuh> = Singleton.api.getGlobalSembuh()
    call.enqueue(object : Callback<Global_sembuh>{
        override fun onFailure(p0: Call<Global_sembuh>, p1: Throwable) {
            sembuh = "Gagal"
        }

        override fun onResponse(p0: Call<Global_sembuh>, p1: Response<Global_sembuh>) {
            var data:Global_sembuh = p1.body()!!
            sembuh = "${data.name} : ${data.value}"
        }

    })

    delay(2000)

    return sembuh
}

private var positif:String = "0"
suspend fun getPositif(): String{
    val call:Call<Global_positif> = Singleton.api.getGlobalPositif()
    call.enqueue(object : Callback<Global_positif>{
        override fun onFailure(p0: Call<Global_positif>, p1: Throwable) {
            positif = p1.message.toString()
        }

        override fun onResponse(p0: Call<Global_positif>, p1: Response<Global_positif>) {
            var data:Global_positif = p1.body()!!
            positif = "${data.name} : ${data.value}"
        }
    })

    delay(2000)

    return positif
}

private var meninggal:String = "0"
suspend fun getMeninggal(): String{
    val call:Call<Global_meninggal> = Singleton.api.getGlobalMeninggal()
    call.enqueue(object : Callback<Global_meninggal>{
        override fun onFailure(p0: Call<Global_meninggal>, p1: Throwable) {
            meninggal = p1.message.toString()
        }

        override fun onResponse(p0: Call<Global_meninggal>, p1: Response<Global_meninggal>) {
            var data:Global_meninggal = p1.body()!!
            meninggal = "${data.name} : ${data.value}"
        }
    })

    delay(2000)

    return meninggal
}
