package com.englishwords

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

class EnglishWordsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(applicationContext)
        val realmConf = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConf)

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.delete(TranslatedWord::class.java)
                val wordList = WordParseUtils.parseFromString(words)
                for(word in wordList)
                    it.insertOrUpdate(word)
//                it.insertOrUpdate(TranslatedWord("Book", "Kniga", id=Random().nextLong()))
//                it.insertOrUpdate(TranslatedWord("Book1", "Kniga1", id=Random().nextLong()))
//                it.insertOrUpdate(TranslatedWord("Book2", "Kniga2", id=Random().nextLong()))
//                it.insertOrUpdate(TranslatedWord("Book3", "Kniga3", id=Random().nextLong()))
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