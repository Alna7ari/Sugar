package step.ahead.group.sugar.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import kotlinx.android.synthetic.main.fragment_user_info.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.fragments.UserInfoFragment
import step.ahead.group.sugar.handlers.UserInfoHandler
import step.ahead.group.sugar.models.UserInfo

class SplashIntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!
        //isButtonsEnabled = false
        showStatusBar(true)
        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment

        addSlide(
            AppIntroFragment.newInstance(

                title = "مرحبا بك في تطبيق...",
                description = "هذا التطبيق يقوم على ...",
                //هنا تحدد الايقونة او الشعار اذا اردت
                //imageDrawable = R.drawable.the_central_icon,
                // هنا اذا اردت خلفية كصورة او شكل بدل عن اختيار لون
                //backgroundDrawable = R.drawable.the_background_image,
                titleColor = Color.YELLOW,
                descriptionColor = Color.RED,
                backgroundColor = Color.BLUE,
                //اذا تحتاج تغير نوع الخط
                //titleTypefaceFontRes = R.font.opensans_regular,
                //descriptionTypefaceFontRes = R.font.opensans_regular,

            )
        )

        addSlide(
            // سلايد مخصص
            UserInfoFragment.newInstance()
        )

        addSlide(
            // للتوضيح فقط ان المعلومات اختيارية واضف ماتريد فقط
            AppIntroFragment.newInstance(
                description = "gggggggggggggggggggggggggggggggggggggg",
                descriptionColor = Color.BLACK,
                backgroundColor = Color.YELLOW,
            )
        )

        addSlide(
            // للتوضيح فقط ان المعلومات اختيارية واضف ماتريد فقط
            AppIntroFragment.newInstance(
                description = "مرحبا انا سكرة, ممرضتك الافتراضية... الخ",
                imageDrawable = R.drawable.ic_launcher_background,
                descriptionColor = Color.WHITE,
                backgroundColor = Color.RED,
            )
        )


    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
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
        Log.d(this.javaClass.name, "$currentFragment")
        super.onNextPressed(currentFragment)
    }
}