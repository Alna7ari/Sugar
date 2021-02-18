package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserInfo : RealmObject(){

    @PrimaryKey
    var primery_key: Int = 1
    var firstName: String = ""
    var lastName: String? = null
    var gender: String? = null
    var age: Int = 10
    var phone: String? = null
    var email: String? = null
    var weight: Int? = null
}