package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmResults
import step.ahead.group.sugar.models.Drug
import step.ahead.group.sugar.models.Sport
import step.ahead.group.sugar.models.UserInfo

class SportHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<Sport>?
        get() = realm.where(Sport::class.java).findAll()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    fun save(sport: Sport) {
        realm.executeTransaction { realm ->
            val maxId = realm.where(Sport::class.java).max("id") ?: 1
            val nextId = maxId.toInt() + 1
            sport.id = nextId
            realm.copyToRealm(sport)
        }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(Sport::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }
    
    
    companion object {
        // define single instance
        private var instance: SportHandler? = null
        fun getInstance(): SportHandler {
            if (instance == null) {
                instance = SportHandler()
            }
            return instance as SportHandler
        }

        fun newInstance(): SportHandler {
            return SportHandler()
        }
    }


}

