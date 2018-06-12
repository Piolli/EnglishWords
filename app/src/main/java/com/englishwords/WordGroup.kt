package com.englishwords

import android.util.Log
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

/**
 * Encapsulates word list into group
 * version 1.0
 * 09.06.2018
 */
open class WordGroup(var groupName: String = "",
                     var words: RealmList<TranslatedWord> = RealmList(),
                     @PrimaryKey var id: String = UUID.randomUUID().toString()
) : RealmObject() {

    @Ignore val LOG = WordGroup::class.java.simpleName

    init {
        Log.d(LOG, this.toString())
    }

    override fun toString(): String {
        return "WordGroup(groupName='$groupName', words=$words, id='$id')"
    }


}