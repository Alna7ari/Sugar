package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import step.ahead.group.sugar.models.Notification


class NotificationHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<Notification>?
        get() = realm.where(Notification::class.java).findAll()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // login user take user and add it to realm

    fun save(notification: Notification) {
        realm.executeTransaction { realm ->
            val maxId = realm.where(Notification::class.java).max("id") ?: 1
            val nextId = maxId.toInt() + 1
            notification.id = nextId
            realm.copyToRealm(notification)
        }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(Notification::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }


    companion object {
        // define single instance
        private var instance: NotificationHandler? = null
        fun getInstance(): NotificationHandler {
            if (instance == null) {
                instance = NotificationHandler()
            }
            return instance as NotificationHandler
        }

        fun newInstance(): NotificationHandler {
            return NotificationHandler()
        }
    }


}


