package com.englishwords.data.source

import com.englishwords.data.TranslatedWord
import com.englishwords.data.WordGroup
import io.realm.RealmResults
import java.util.ArrayList

interface WordGroupDataSource {

    interface LoadWordGroupsCallback {
        fun onWordGroupsLoaded(wordGroups: List<WordGroup>)
        fun onDataNotAvailable()
    }

    interface GetWordGroupCallback {
        fun onWordGroupLoaded(wordGroup: WordGroup)
        fun onDataNotAvailable()
    }

    /**
     * Load all word groups
     */
    fun getWordGroupsList(callback: LoadWordGroupsCallback)

    /**
     * Get word group with [groupId]
     */
    fun getWordGroup(groupId: String, callback: GetWordGroupCallback)

    /**
     * To default priority for all words in [groupId]
     */
    fun changeToDefaultPriorityWordsInGroup(groupId: String)

    /**
     * Save added [word] to the [groupId]
     */
    fun saveWordToGroup(word: TranslatedWord, groupId: String)

    /**
     * Up priority [wordId] for sorting output words
     */
    fun incrementWordPriorityInGroup(wordId: String, groupId: String)

    /**
     * Down priority [wordId] for sorting output words
     */
    fun decrementWordPriorityInGroup(wordId: String, groupId: String)

    /**
     * Remove [wordId] from [groupId]
     */
    fun removeWordFromGroup(wordId: String, groupId: String)

    /**
     * Remove [groupId]
     */
    fun removeWordGroup(groupId: String)
}