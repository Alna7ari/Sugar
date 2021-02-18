package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import step.ahead.group.sugar.models.UserInfo

class UserInfoHandler
private constructor() {
    // define realm
    private val realm: Realm

    val isUserLoggedIn: Boolean
        get() = realm.where(UserInfo::class.java).findFirst() != null

    val userInfo: UserInfo?
        get() = realm.where(UserInfo::class.java).findFirst()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // login user take user and add it to realm

    fun setInfo(user: UserInfo) {

        if (realm.where(UserInfo::class.java).findFirst() == null) {

            realm.executeTransaction { realm -> realm.copyToRealm(user) }

        } else {
            logout()
            setInfo(user)
        }


    }

    private fun logout() {
        realm.executeTransaction { realm ->
            realm.where(UserInfo::class.java).findAll().deleteAllFromRealm()
        }
        //realm.executeTransaction { realm -> realm.delete(UserInfo::class.java) }
    }
    
    
    companion object {
        // define single instance
        private var instance: UserInfoHandler? = null

        // get singletone from LoginHelper
        fun getInstance(overrideInstanse: Boolean = false): UserInfoHandler {
            if (overrideInstanse) {
                return UserInfoHandler()
            }
            if (instance == null) {
                instance = UserInfoHandler()
            }
            return instance as UserInfoHandler
        }

        // get new Instance (new Object) from this class
        fun newInstance(): UserInfoHandler {
            return UserInfoHandler()
        }
    }


}

