package step.ahead.group.sugar.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getDate(milliSeconds: Long?, dateFormat: String? = null): String? {
        val dateFormatIn = dateFormat ?: "dd/MM"
        milliSeconds ?: return "غير محدد"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormatIn, Locale.getDefault())

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}