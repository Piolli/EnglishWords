package com.englishwords

import android.content.Context
import io.realm.Realm
import java.util.*

class WordRepositoryImpl(context: Context) : WordRepository {

    override fun getWordGroups(): ArrayList<WordGroup> {
        val results = Realm.getDefaultInstance().where(WordGroup::class.java).findAll()
        val array = arrayListOf<WordGroup>()
        results.forEach {
            array.add(it)
        }
        return array
    }

    override fun saveWordToGroup(word: TranslatedWord, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                group?.words?.add(word)
            }
        }
    }

    override fun incrementPriority(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.incrementPriority()
            }
        }
    }

    override fun decrementPriority(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.decrementPriority()
            }
        }
    }

    override fun removeWord(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.deleteFromRealm()
            }
        }
    }
}