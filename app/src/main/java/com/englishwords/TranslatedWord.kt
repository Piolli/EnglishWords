package com.englishwords

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * 31.06.2018
 * version 1.0
 */
open class TranslatedWord(override var word: String = "",
                     override var translate: String = "",
                     override var wordLanguage: String = "English",
                     override var priority: Int = 5,
                     override var id: String = UUID.randomUUID().toString())
    : RealmObject(), Word  {

    override fun toString(): String {
        return "TranslatedWord(word='$word', translate='$translate', wordLanguage=$wordLanguage, priority=$priority)"
    }

    fun incrementPriority() {
        if(priority < 5)
            priority++
    }

    fun decrementPriority() {
        if(priority > 0)
            priority--
    }

    fun toDefaultPriority() {
        priority = 5
    }
}