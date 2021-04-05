package step.ahead.group.sugar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.appintro.SlidePolicy
import kotlinx.android.synthetic.main.fragment_user_info.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.activities.SplashIntroActivity
import step.ahead.group.sugar.handlers.UserInfoHandler
import step.ahead.group.sugar.models.UserInfo


class UserInfoFragment(private val splashIntroActivity: SplashIntroActivity) : Fragment(), SlidePolicy {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_btn_three.setOnClickListener {
            if (! isPolicyRespected) {
                onUserIllegallyRequestedNextPage()
                return@setOnClickListener
            }
            splashIntroActivity.nextSlide(this)
        }
    }

    override val isPolicyRespected: Boolean
        get() = !first_name_input.text.isNullOrEmpty() && !age_input.text.isNullOrEmpty()

    override fun onUserIllegallyRequestedNextPage() {
        Toast.makeText(
            requireContext(),
            "لايمكن التجاوز قبل كتابة معلومات صحيحة!!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun saveInfo() {
        val userInfo = UserInfo()
        userInfo.firstName = first_name_input.text.toString()
        userInfo.age = age_input.text.toString().toInt()

        UserInfoHandler.getInstance().setInfo(userInfo)
    }
    companion object {
        fun newInstance(splashIntroActivity: SplashIntroActivity) : UserInfoFragment {
            return UserInfoFragment(splashIntroActivity)
        }
    }
}