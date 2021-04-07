package step.ahead.group.sugar.dialogs


import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout
import com.afollestad.vvalidator.form
import kotlinx.android.synthetic.main.dialog_add_result.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.handlers.TestResultHandler
import step.ahead.group.sugar.models.TestResult


/**
 * A simple [Fragment] subclass.
 */
class AddResultDialog() : SupportBlurDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_result_dailog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        form {
            input(result) {
                isNumber().greaterThan(30).description("قيمة خاطئة")
            }
            submitWith(save_btn) {
                val testResult = TestResult()
                testResult.result = result.text.toString().toDoubleOrNull()
                testResult.createdAt = System.currentTimeMillis() / 1000
                TestResultHandler.getInstance().save(testResult)
                Toast.makeText(context, "تم الحفظ بنجاح!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun isActionBarBlurred(): Boolean { return true }

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
