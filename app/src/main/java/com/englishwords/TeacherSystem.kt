package com.englishwords

import android.content.Context
import android.util.Log


/**
 * Respond for save right and wrong translate answers
 * 06.06.2018
 * version 1.0
 */
class TeacherSystem(private val repository: WordRepository, val groupId: String) {
    val LOG = TeacherSystem::class.java.simpleName

    /**
     * Get sorted word by descending
     */
    fun getSortedWords(): ArrayList<TranslatedWord> {
        val groups = repository.getWordGroups()
        val realmWords = groups.find { it.id == groupId }?.words

        if(realmWords == null) {
            Log.d(LOG, "get words from db is null")
            throw Exception("DB Words is null")
        }

        val words = ArrayList(realmWords)
        words.sortWith(Comparator { o1, o2 ->
            o2.priority.compareTo(o1.priority)
        })
        return ArrayList(words)
    }

    fun toDefaultWordsThisGroup() {
        val groups = repository.getWordGroupsRealmResult()
        //Find groups and each word to default
        groups.find { it.id == groupId }
                ?.words
                ?.forEach { it.toDefaultPriority() }
    }

    fun onRightAnswer(wordId: String) {
        repository.decrementPriority(wordId, groupId)
    }

    fun onWrongAnswer(wordId: String) {
        repository.incrementPriority(wordId, groupId)
    }

    fun deleteThisGroup() {
        repository.removeWordGroup(groupId)
    }
}