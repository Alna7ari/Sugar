package step.ahead.group.sugar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import step.ahead.group.sugar.R
import step.ahead.group.sugar.handlers.UserInfoHandler

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null

    
    // وقت الانتظار قبل الانتقال بالملي ثانية
    private val delayTime: Long = 500
    private val mRunnable: Runnable = Runnable {
        if (isFinishing) return@Runnable
        var activity = SplashIntroActivity::class
        // هنا عند تحقق هذا الشرط سينتقل المستخدم الى الواجهه الرئيسية ولان الواجهه ليست جاهزة جعلتة ينتقل لنفس الواجهه
        if (UserInfoHandler.getInstance().isUserLoggedIn) activity = SplashIntroActivity::class

        val intent = Intent(this, activity.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Initialize the Handler
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, delayTime)
    }
    override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}