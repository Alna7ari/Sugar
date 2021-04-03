package step.ahead.group.sugar.webservices

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import step.ahead.group.sugar.requests.TestResultRequest
import step.ahead.group.sugar.responses.TestResultResponse


interface API {

    @POST("result")
    fun getResult(@Body request: TestResultRequest,  @Header("Authorization") auth : String = "56dh76f79f9e0js7n47"): Call<TestResultResponse>
    @POST("check")
    fun checkConnection(@Header("Authorization") auth : String = "56dh76f79f9e0js7n47"): Call<TestResultResponse>
}