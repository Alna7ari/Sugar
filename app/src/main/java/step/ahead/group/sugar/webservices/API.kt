package step.ahead.group.sugar.webservices

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import step.ahead.group.sugar.requests.TestResultRequest
import step.ahead.group.sugar.responses.TestResultResponse


interface API {

    @POST("result")
    fun getResult(@Body request: TestResultRequest): Call<TestResultResponse>

}