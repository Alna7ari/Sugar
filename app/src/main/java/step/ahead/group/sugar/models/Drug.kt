package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Drug : RealmObject(){
    @PrimaryKey
    var id: Int = 1
    var name: String? = null
    var strength: Int? = null
    var useCount: Int? = null
    var useTime: Long = 0
}