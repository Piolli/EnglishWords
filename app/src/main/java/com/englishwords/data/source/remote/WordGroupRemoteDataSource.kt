package com.englishwords.data.source.remote

import com.englishwords.data.TranslatedWord
import com.englishwords.data.source.WordGroupDataSource

object WordGroupRemoteDataSource : WordGroupDataSource {
    override fun getWordGroupsList(callback: WordGroupDataSource.LoadWordGroupsCallback) {
        TODO()
    }

    override fun getWordGroup(groupId: String, callback: WordGroupDataSource.GetWordGroupCallback) {
        TODO()
    }

    override fun changeToDefaultPriorityWordsInGroup(groupId: String) {
        TODO()
    }

    override fun saveWordToGroup(word: TranslatedWord, groupId: String) {
        TODO()
    }

    override fun incrementWordPriorityInGroup(wordId: String, groupId: String) {
        TODO()
    }

    override fun decrementWordPriorityInGroup(wordId: String, groupId: String) {
        TODO()
    }

    override fun removeWordFromGroup(wordId: String, groupId: String) {
        TODO()
    }

    override fun removeWordGroup(groupId: String) {
        TODO()
    }
}