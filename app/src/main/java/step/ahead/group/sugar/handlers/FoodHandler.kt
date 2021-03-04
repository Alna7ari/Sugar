package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmResults
import step.ahead.group.sugar.models.Food
import step.ahead.group.sugar.models.UserInfo

class FoodHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<Food>?
        get() = realm.where(Food::class.java).findAll()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    fun save(drug: Food) {
        realm.executeTransaction { realm -> realm.copyToRealm(drug) }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(Food::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }
    
    
    companion object {
        // define single instance
        private var instance: FoodHandler? = null
        fun getInstance(): FoodHandler {
            if (instance == null) {
                instance = FoodHandler()
            }
            return instance as FoodHandler
        }

        fun newInstance(): FoodHandler {
            return FoodHandler()
        }
    }


}

