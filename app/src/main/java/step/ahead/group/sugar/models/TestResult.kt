package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TestResult : RealmObject(){

    @PrimaryKey
    var id: Int = 1
    var result: Double? = null
    var type: String? = null
    var updatedAt: Long = 0
    var createdAt: Long = 0
}