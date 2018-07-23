package com.englishwords.wordgroup

import android.support.annotation.NonNull
import com.englishwords.data.WordGroup
import com.englishwords.data.source.WordGroupDataSource
import com.englishwords.data.source.WordGroupRepository

class WordGroupListPresenter(
        @NonNull val wordGroupRepository: WordGroupRepository,
        @NonNull var view: WordGroupListContract.View?
) : WordGroupListContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun loadWordGroupList() {
        wordGroupRepository.getWordGroupsList(object: WordGroupDataSource.LoadWordGroupsCallback {
            override fun onWordGroupsLoaded(wordGroups: List<WordGroup>) {
                view?.setWordGroupList(wordGroups)
            }

            override fun onDataNotAvailable() {
                TODO("View show error message")
            }
        })
    }



    override fun destroy() {
        view = null
    }
}