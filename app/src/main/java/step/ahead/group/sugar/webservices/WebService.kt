package step.ahead.group.sugar.webservices

import android.util.Log
import com.alna7ari.alnaharionline.webservices.Urls.MAIN_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import io.realm.RealmObject
import com.google.gson.FieldAttributes
import com.google.gson.ExclusionStrategy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import step.ahead.group.sugar.requests.TestResultRequest
import step.ahead.group.sugar.responses.TestResultResponse
import step.ahead.group.sugar.utils.ResponseValidation
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection


/**
 * hendiware 2016
 */

class WebService() {
    private val api: API

    init {

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

        val gson = GsonBuilder()
                .setExclusionStrategies(object : ExclusionStrategy {
                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        return f.declaringClass == RealmObject::class.java
                    }

                    override fun shouldSkipClass(clazz: Class<*>): Boolean {
                        return false
                    }
                })
                .create()


        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(MAIN_URL)
                .client(okHttpClient)
                .build()


        api = retrofit.create(API::class.java)
    }

    companion object {
        private var instance: WebService? = null

        private fun getInstance(): WebService {
            if (instance == null) {
                instance = WebService()
            }
            return instance as WebService
        }
        fun post(request: TestResultRequest, onResponse: (response: TestResultResponse) -> Unit) {
            getInstance().api.getResult(request).enqueue(object : Callback<TestResultResponse> {

                override fun onResponse(call: Call<TestResultResponse>, response: Response<TestResultResponse>) {
                    var result = response.body()
                    val errors = ResponseValidation().responseValidate(response)
                    if (errors != null) result = errors
                    onResponse(result!!)

                }


                override fun onFailure(call: Call<TestResultResponse>, t: Throwable) {
                    onResponse(ResponseValidation().defaultError())
                }
            })
        }
    }
}
