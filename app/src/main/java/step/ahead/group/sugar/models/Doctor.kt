package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Doctor : RealmObject(){
    @PrimaryKey
    var id: Int = 1
    var name: String? = null
    var phone: String? = null
    var clinic: Int? = null
    var email: String? = null
    var reviewTime: Long? = null
}