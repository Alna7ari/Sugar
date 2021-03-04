package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Food : RealmObject(){
    @PrimaryKey
    var id: Int = 1
    var name: String? = null
    var type: String? = null
    var time: Long = 0
    var isAllowed: Boolean = true
}