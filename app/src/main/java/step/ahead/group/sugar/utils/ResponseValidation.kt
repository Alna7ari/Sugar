package step.ahead.group.sugar.utils

import com.google.gson.Gson
import retrofit2.Response
import step.ahead.group.sugar.responses.TestResultResponse

class ResponseValidation {

    fun responseValidate(response: Response<*>): TestResultResponse?{
        if (response.isSuccessful) return null
        return try {
            Gson().fromJson(
                response.errorBody()!!.charStream(),
                TestResultResponse::class.java
            )
        } catch (e: Throwable) {
            defaultError()
        }

    }

    fun defaultError(): TestResultResponse {
        val error = TestResultResponse()
        error.status = 0
        error.message = "حصل خطاء غير معروف"
        return error
    }
}