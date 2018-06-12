package com.englishwords

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import java.util.*

class EnglishWordsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)
        val realmConf = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConf)

        exampleData()
    }

    private fun exampleData() {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.delete(WordGroup::class.java)
                val wordList = WordParseUtils.parseFromString(words)
                val realmListWords = RealmList<TranslatedWord>()
                wordList.forEach { realmListWords.add(it) }
                val group = WordGroup()
                val group1 = WordGroup()
                val group2 = WordGroup()
                val group3 = WordGroup()

                group.groupName = "WordGroup1"
                group.words = realmListWords

                group1.groupName = "English popular words"
                group1.words = realmListWords

                group2.groupName = "Best words for beginner in english language"
                group2.words = realmListWords

                group3.groupName = "Not bad"
                group3.words = realmListWords

                it.insertOrUpdate(group)
                it.insertOrUpdate(group1)
                it.insertOrUpdate(group2)
                it.insertOrUpdate(group3)
            }
        }
    }

    val words =
    "reproduce - воспроизводить\n" +
    "retrieval system - поисковая системапоисков\n" +
    "prior - предшествующий\n" +
    "except - кроме, за исключением\n" +
    "brief - краткий, короткий\n"

}