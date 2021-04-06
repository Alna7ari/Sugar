package step.ahead.group.sugar.dialogs


import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.afollestad.vvalidator.form
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment
import kotlinx.android.synthetic.main.add_doctors_fragment.close_btn
import kotlinx.android.synthetic.main.add_doctors_fragment.name
import kotlinx.android.synthetic.main.add_doctors_fragment.save_btn
import kotlinx.android.synthetic.main.add_doctors_fragment.review_time
import kotlinx.android.synthetic.main.add_doctors_fragment.number_doc
import kotlinx.android.synthetic.main.add_doctors_fragment.email_doc
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
        close_btn.setOnClickListener { dismiss() }
        form {
            input(name) {
                isNotEmpty().description("لايمكن ان تكون الاسم فارغا")
            }
            input(review_time) {
                isNumber().greaterThan(1).description("يجب كتابة رقم صحيح")
            }
            input(number_doc) {
                isNumber().greaterThan(0).description("يجب كتابة رقم صحيح")
            }
            input(email_doc) {
                isNumber().greaterThan(0).description("يجب كتابة رقم صحيح")
            }
            submitWith(save_btn) { onValidationSucceeded() }
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

    private fun onValidationSucceeded() {
        val doctor = Doctor()
        doctor.name = name.text.toString()
        doctor.clinic = review_time.text.toString().toIntOrNull()
        doctor.phone = number_doc.text.toString()
        doctor.email = email_doc.text.toString()
        DoctorHandler.getInstance().save(doctor)
        Toast.makeText(context, "تم الحفظ بنجاح!", Toast.LENGTH_SHORT).show()
        dismiss()
    }
}
