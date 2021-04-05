package step.ahead.group.sugar.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment


import step.ahead.group.sugar.R
import step.ahead.group.sugar.fragments.AppIntroCustomNextButtonFragment
import step.ahead.group.sugar.fragments.UserInfoFragment

class SplashIntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!
        isButtonsEnabled = false
        isIndicatorEnabled = false
        //showStatusBar(true)
        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.user_intro_info_one))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.user_intro_info_two))
        addSlide(UserInfoFragment.newInstance(this))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.instractions_first))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.instractions_second))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.instractions_thierd))

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onNextPressed(currentFragment: Fragment?) {
        if (currentFragment is UserInfoFragment) {
            currentFragment.saveInfo()
        }
        Log.d(this.javaClass.name + ": onNextPressed", "$currentFragment")
        super.onNextPressed(currentFragment)
    }

    fun nextSlide(currentFragment: Fragment?, isLastSlide: Boolean = false) {
        onNextPressed(currentFragment)
        goToNextSlide(isLastSlide)
    }

    fun onDone(view: View) {
        onDonePressed(view.findFragment())
    }

    fun nextSlide(view: View) {
        nextSlide(view.findFragment(), false)
    }
}