package com.englishwords.data.source.local

import com.englishwords.data.TranslatedWord
import com.englishwords.data.WordGroup
import com.englishwords.data.source.WordGroupDataSource
import io.realm.Realm

object WordGroupLocalDataSource : WordGroupDataSource {
    override fun getWordGroupsList(callback: WordGroupDataSource.LoadWordGroupsCallback) {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(WordGroup::class.java).findAll()
        if(results.isNotEmpty()) {
            val array = arrayListOf<WordGroup>()
            results.forEach {
                array.add(it)
            }
            callback.onWordGroupsLoaded(array)
        }
        else {
            callback.onDataNotAvailable()
        }
    }

    override fun getWordGroup(groupId: String, callback: WordGroupDataSource.GetWordGroupCallback) {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(WordGroup::class.java).findAll()
        if(results.isNotEmpty()) {
            val wordGroup = results.find { it.id == groupId }
            if(wordGroup != null) {
                callback.onWordGroupLoaded(wordGroup)
            }
            else {
                callback.onDataNotAvailable()
            }
        }
        else {
            callback.onDataNotAvailable()
        }
    }

    override fun changeToDefaultPriorityWordsInGroup(groupId: String) {
        val realm = Realm.getDefaultInstance()
        val groups = realm.where(WordGroup::class.java).findAll()
        if(groups.isNotEmpty()) {
            groups.find { it.id == groupId }
                    ?.words
                    ?.forEach { it.toDefaultPriority() }
        }
        else {

        }
    }

    override fun saveWordToGroup(word: TranslatedWord, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                group?.words?.add(word)
            }
        }
    }

    override fun incrementWordPriorityInGroup(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.incrementPriority()
            }
        }
    }

    override fun decrementWordPriorityInGroup(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.decrementPriority()
            }
        }
    }

    override fun removeWordFromGroup(wordId: String, groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                val word = group?.words?.find { it.id == wordId }
                word?.deleteFromRealm()
            }
        }
    }

    override fun removeWordGroup(groupId: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val group = it.where(WordGroup::class.java).equalTo("id", groupId).findFirst()
                group?.deleteFromRealm()
            }
        }
    }

}