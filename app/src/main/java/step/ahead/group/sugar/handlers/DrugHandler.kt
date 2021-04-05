package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmResults
import step.ahead.group.sugar.models.Drug
import step.ahead.group.sugar.models.UserInfo

class DrugHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<Drug>?
        get() = realm.where(Drug::class.java).findAll()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // login user take user and add it to realm

    fun save(drug: Drug) {
        realm.executeTransaction { realm -> realm.copyToRealm(drug) }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(Drug::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }
    
    
    companion object {
        // define single instance
        private var instance: DrugHandler? = null
        fun getInstance(): DrugHandler {
            if (instance == null) {
                instance = DrugHandler()
            }
            return instance as DrugHandler
        }

        fun newInstance(): DrugHandler {
            return DrugHandler()
        }
    }


}

