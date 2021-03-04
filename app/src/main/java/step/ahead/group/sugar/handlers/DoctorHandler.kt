package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmResults
import step.ahead.group.sugar.models.Doctor
import step.ahead.group.sugar.models.UserInfo

class DoctorHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<Doctor>?
        get() = realm.where(Doctor::class.java).findAll()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // login user take user and add it to realm

    fun save(drug: Doctor) {
        realm.executeTransaction { realm -> realm.copyToRealm(drug) }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(Doctor::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }
    
    
    companion object {
        // define single instance
        private var instance: DoctorHandler? = null
        fun getInstance(): DoctorHandler {
            if (instance == null) {
                instance = DoctorHandler()
            }
            return instance as DoctorHandler
        }

        fun newInstance(): DoctorHandler {
            return DoctorHandler()
        }
    }


}

