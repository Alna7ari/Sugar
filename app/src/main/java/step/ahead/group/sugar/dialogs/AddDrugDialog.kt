package step.ahead.group.sugar.dialogs


import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout
import step.ahead.group.sugar.R


/**
 * A simple [Fragment] subclass.
 */
class AddDrugDialog() : SupportBlurDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_share, container, false)
    }

    override fun isActionBarBlurred(): Boolean {
        // Enable or disable the blur effect on the action bar.
        // Disabled by default.
        return true
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window
        val size = Point()

        window!!.windowManager.defaultDisplay.getSize(size)

        val width = size.x
        val height = size.y

        val heightPercentage = (height * 0.95).toInt() // ConstraintLayout.LayoutParams.WRAP_CONTENT
        window.setLayout((width * 0.95).toInt(), heightPercentage)
        window.setGravity(Gravity.CENTER)
    }

}
