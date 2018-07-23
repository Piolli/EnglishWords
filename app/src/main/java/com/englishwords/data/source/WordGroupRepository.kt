package com.englishwords.data.source

import android.support.annotation.NonNull
import com.englishwords.data.TranslatedWord

class WordGroupRepository(
        @NonNull private val localWordGroupDataSource: WordGroupDataSource,
        @NonNull private val remoteWordGroupDataSource: WordGroupDataSource
) : WordGroupDataSource {


    override fun getWordGroupsList(callback: WordGroupDataSource.LoadWordGroupsCallback) {
        localWordGroupDataSource.getWordGroupsList(callback)
    }

    override fun getWordGroup(groupId: String, callback: WordGroupDataSource.GetWordGroupCallback) {
        localWordGroupDataSource.getWordGroup(groupId, callback)
    }

    override fun changeToDefaultPriorityWordsInGroup(groupId: String) {

    }

    override fun saveWordToGroup(word: TranslatedWord, groupId: String) {
        localWordGroupDataSource.saveWordToGroup(word, groupId)
    }

    override fun incrementWordPriorityInGroup(wordId: String, groupId: String) {
        localWordGroupDataSource.incrementWordPriorityInGroup(wordId, groupId)
    }

    override fun decrementWordPriorityInGroup(wordId: String, groupId: String) {
        localWordGroupDataSource.decrementWordPriorityInGroup(wordId, groupId)
    }

    override fun removeWordFromGroup(wordId: String, groupId: String) {
        localWordGroupDataSource.removeWordFromGroup(wordId, groupId)
    }

    override fun removeWordGroup(groupId: String) {
        localWordGroupDataSource.removeWordGroup(groupId)
    }
}