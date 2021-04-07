package step.ahead.group.sugar.handlers

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import step.ahead.group.sugar.models.Drug
import step.ahead.group.sugar.models.TestResult

class TestResultHandler
private constructor() {
    private val realm: Realm

    val getAll: RealmResults<TestResult>?
        get() = realm.where(TestResult::class.java).findAll()

    val getLast: TestResult
        get() = realm.where(TestResult::class.java).findAll().last(null) ?: defaultResult()

    init {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // login user take user and add it to realm

    fun save(testResult: TestResult) {
        realm.executeTransaction { realm ->
            val maxId = realm.where(TestResult::class.java).max("id") ?: 1
            val nextId = maxId.toInt() + 1
            testResult.id = nextId
            realm.copyToRealm(testResult)
        }
    }

    fun delete(id: Int) {
        realm.executeTransaction { realm ->
            realm.where(TestResult::class.java).equalTo("id", id).findFirst()?.deleteFromRealm()
        }
    }

    private fun defaultResult(): TestResult {
        val testResult = TestResult()
        testResult.result = 0.0
        testResult.type = "default"
        return  testResult
    }

    companion object {
        // define single instance
        private var instance: TestResultHandler? = null
        fun getInstance(): TestResultHandler {
            if (instance == null) {
                instance = TestResultHandler()
            }
            return instance as TestResultHandler
        }

        fun newInstance(): TestResultHandler {
            return TestResultHandler()
        }
    }


}

