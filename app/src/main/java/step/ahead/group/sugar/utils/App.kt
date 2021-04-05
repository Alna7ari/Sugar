package step.ahead.group.sugar.utils

import android.app.Application
import android.content.Intent
import androidx.multidex.MultiDex
import io.realm.Realm
import step.ahead.group.sugar.webservices.EventSourceService

class App: Application() {
    // في هذا الملف يتم تهيئة وتجهيز قواعد البيانات عند تشغيل البرنامج
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Realm.init(this)
        //startService(Intent(this, EventSourceService::class.java))
    }
}