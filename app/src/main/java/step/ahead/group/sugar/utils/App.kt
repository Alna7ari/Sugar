package step.ahead.group.sugar.utils

import android.app.Application
import io.realm.Realm

class App: Application() {
    // في هذا الملف يتم تهيئة وتجهيز قواعد البيانات عند تشغيل البرنامج
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}