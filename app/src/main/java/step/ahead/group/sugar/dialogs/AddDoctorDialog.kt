package step.ahead.group.sugar.dialogs


import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment
import kotlinx.android.synthetic.main.add_doctors_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.handlers.DoctorHandler
import step.ahead.group.sugar.models.Doctor


/**
 * A simple [Fragment] subclass.
 */
class AddDoctorDialog() : SupportBlurDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_doctors_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save_doctor.setOnClickListener {
            val doctor = Doctor()
            doctor.name = name_drug_input.text.toString()

            DoctorHandler.getInstance().save(doctor)
        }
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
