package step.ahead.group.sugar.utils

import android.content.Context

object StringUtils {
    fun format(value: Float?): String? {
        return trimZero(value.toString())
    }
    fun format(value: Double?): String? {
        return trimZero(value.toString())
    }
    fun format(value: Int?): String? {
        return trimZero(value.toString())
    }
    fun format(value: String?): String? {
        return trimZero(value)
    }
    private fun trimZero(value: String?): String? {
        return if (!value.isNullOrEmpty()) {
            if (value.indexOf(".") < 0) {
                value

            } else {
                value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
            }

        } else {
            value
        }
    }
}