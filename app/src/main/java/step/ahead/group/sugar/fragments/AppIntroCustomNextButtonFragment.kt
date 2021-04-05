package step.ahead.group.sugar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import step.ahead.group.sugar.R
import step.ahead.group.sugar.activities.SplashIntroActivity


class AppIntroCustomNextButtonFragment(private val layoutId: Int) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(layoutId , container, false)
    }

}