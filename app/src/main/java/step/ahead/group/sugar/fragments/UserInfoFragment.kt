package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.appintro.SlidePolicy
import kotlinx.android.synthetic.main.fragment_user_info.*
import step.ahead.group.sugar.R


class UserInfoFragment : Fragment(), SlidePolicy {

    var isFocused = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            isFocused = false
        }
    }
    override val isPolicyRespected: Boolean
        get() = !first_name.text.isNullOrEmpty() && !age.text.isNullOrEmpty()

    override fun onUserIllegallyRequestedNextPage() {
        Toast.makeText(
            requireContext(),
            "لايمكن التجاوز قبل كتابة معلومات صحيحة!!",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        fun newInstance() : UserInfoFragment {
            return UserInfoFragment()
        }
    }
}