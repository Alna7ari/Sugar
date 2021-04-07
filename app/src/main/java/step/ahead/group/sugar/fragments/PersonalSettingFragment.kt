package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.profie_fragment.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.handlers.UserInfoHandler


class PersonalSettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = UserInfoHandler.getInstance().userInfo ?: return

        first_name.text = Editable.Factory.getInstance().newEditable(user.firstName)
        age.text = Editable.Factory.getInstance().newEditable(user.age.toString())
    }
}