package step.ahead.group.sugar.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import step.ahead.group.sugar.R

class FragmentUtils {

    private var layoutId = R.id.fragment
    fun open(activity: FragmentActivity?, fragment: Fragment, addToBackStack: Boolean = false) {
        if (activity == null) return

        val changer =
            activity
                .supportFragmentManager
                .beginTransaction()
                .replace(layoutId, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            changer.addToBackStack(null)
        } else {
            changer.disallowAddToBackStack()
        }
        changer.commit()
    }

    fun setLayoutId(id: Int): FragmentUtils {
        layoutId = id
        return this
    }
}