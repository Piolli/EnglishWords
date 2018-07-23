package com.englishwords.wordgroup

import com.englishwords.data.TranslatedWord
import com.englishwords.data.WordGroup
import com.englishwords.mvpbase.BasePresenter
import com.englishwords.mvpbase.BaseView

interface WordGroupListContract {

    interface View : BaseView<Presenter> {

        fun setWordGroupList(wordGroupList: List<WordGroup>)

    }

    interface Presenter : BasePresenter {

        fun loadWordGroupList()

    }
}