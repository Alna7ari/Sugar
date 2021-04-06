package step.ahead.group.sugar.dialogs


import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment
import com.afollestad.vvalidator.form
import kotlinx.android.synthetic.main.add_sport_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.handlers.SportHandler
import step.ahead.group.sugar.models.Sport


/**
 * A simple [Fragment] subclass.
 */
class AddSportDialog() : SupportBlurDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_sport_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close_btn.setOnClickListener { dismiss() }
        form {
            input(denaid_food) {
                isNotEmpty().description("لايمكن ان تكون الاسم فارغا")
            }
            input(allawed_food) {
                isNumber().greaterThan(1).description("يجب كتابة رقم صحيح")
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
        val sport = Sport()
        sport.type = denaid_food.text.toString()
        //sport.time = time_sport.text.toString()
        SportHandler.getInstance().save(sport)
        Toast.makeText(context, "تم الحفظ بنجاح!", Toast.LENGTH_SHORT).show()
        dismiss()
    }

}
