package com.englishwords.wordlist

import android.support.annotation.NonNull
import com.englishwords.data.TranslatedWord
import com.englishwords.data.WordGroup
import com.englishwords.data.source.WordGroupDataSource
import com.englishwords.data.source.WordGroupRepository
import com.englishwords.wordgroup.WordGroupListContract

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
class WordListPresenter(@NonNull val wordGroupRepository: WordGroupRepository,
                        @NonNull var view: WordListContract.View?)
    : WordListContract.Presenter
{
    init {
        view?.setPresenter(this)
    }

    override fun loadWordList(groupId: String) {
        wordGroupRepository.getWordGroup(groupId, object: WordGroupDataSource.GetWordGroupCallback {
            override fun onWordGroupLoaded(wordGroup: WordGroup) {
                view?.setWordList(wordGroup.words)
            }

            override fun onDataNotAvailable() {

            }
        })
    }

    override fun onRemoveWord(wordId: String, groupId: String) {
        wordGroupRepository.removeWordFromGroup(wordId, groupId)
    }

    override fun onEditWord(wordId: String, newWord: TranslatedWord, groupId: String) {

    }

    override fun destroy() {
        view = null
    }
}