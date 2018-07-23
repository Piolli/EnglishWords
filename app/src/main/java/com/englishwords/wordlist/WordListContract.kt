package com.englishwords.wordlist

import com.englishwords.data.TranslatedWord
import com.englishwords.data.Word
import com.englishwords.data.WordGroup
import com.englishwords.mvpbase.BasePresenter
import com.englishwords.mvpbase.BaseView

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
interface WordListContract {

    interface View : BaseView<Presenter> {

        fun setWordList(wordList: List<TranslatedWord>)

    }

    interface Presenter : BasePresenter {

        fun loadWordList(groupId: String)

        fun onRemoveWord(wordId: String, groupId: String)

        fun onEditWord(wordId: String, newWord: TranslatedWord, groupId: String)

    }
}