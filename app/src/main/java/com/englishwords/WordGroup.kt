package com.englishwords

import io.realm.RealmObject
import java.util.*
import kotlin.collections.ArrayList

/**
 * Encapsulates word list into group
 * version 1.0
 * 09.06.2018
 */
class WordGroup(var groupName: String,
                var words: ArrayList<TranslatedWord>,
                var id: String = UUID.randomUUID().toString()
) : RealmObject() {

}