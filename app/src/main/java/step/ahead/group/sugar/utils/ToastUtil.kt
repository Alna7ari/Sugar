package step.ahead.group.sugar.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

class ToastUtil(val context: Context?, val text: String, val isLong: Boolean = false) {
    init {
        val duration = if(isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(context, text, duration).show()
    }
}