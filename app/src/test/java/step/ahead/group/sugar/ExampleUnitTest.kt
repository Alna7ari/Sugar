package step.ahead.group.sugar

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val messagesBody = "Glucose level: \n 84.43 \n mg/dl"
        val result = messagesBody.substringAfter("level:").substringBefore("mg/dl").replace(" ", "").toDoubleOrNull()
        assertEquals(result, 84.43)
    }
    @Test
    fun addition_isInt() {
        val messagesBody = "Glucose level: \n 84 \n mg/dl"
        val result = messagesBody.substringAfter("level:").substringBefore("mg/dl").replace(" ", "").toDoubleOrNull()
        assertEquals(result, 84.0)
    }
}