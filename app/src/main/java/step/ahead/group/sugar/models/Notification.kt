package step.ahead.group.sugar.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Notification : RealmObject(){

    @PrimaryKey
    var id: Int = 1
    var type: String? = null
    var avatar: String? = null
    var message: String? = null
    var title: String? = null
    var createdAt: Long = 0
}