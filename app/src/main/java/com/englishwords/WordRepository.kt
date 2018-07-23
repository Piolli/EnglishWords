package com.englishwords

import com.englishwords.data.TranslatedWord
import com.englishwords.data.WordGroup
import io.realm.RealmResults
import java.util.ArrayList

/**
 * API get/save words
 */
interface WordRepository {
    /**
     * Get all word groups
     */
    fun getWordGroups(): ArrayList<WordGroup>

    /**
     * Get realm list for group wordGroupListAdapter
     */
    fun getWordGroupsRealmResult(): RealmResults<WordGroup>

    /**
     * Save added [word] to the [groupId]
     */
    fun saveWordToGroup(word: TranslatedWord, groupId: String)

    /**
     * Up priority [wordId] for sorting output words
     */
    fun incrementPriority(wordId: String, groupId: String)

    /**
     * Down priority [wordId] for sorting output words
     */
    fun decrementPriority(wordId: String, groupId: String)

    /**
     * Remove [wordId] from [groupId]
     */
    fun removeWord(wordId: String, groupId: String)

    /**
     * Remove [groupId]
     */
    fun removeWordGroup(groupId: String)
}
