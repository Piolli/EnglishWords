package com.englishwords.wordlist

import com.englishwords.data.TranslatedWord

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
interface WordListAdapterListener {

    fun onRemoveWord(wordId: String)

    fun onEditWord(wordId: String, newWord: TranslatedWord)

}