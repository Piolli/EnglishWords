package com.englishwords.wordlist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.englishwords.ChangeGroupActivity
import com.englishwords.Injection

import com.englishwords.R
import com.englishwords.data.TranslatedWord
import com.englishwords.data.Word
import kotlinx.android.synthetic.main.fragment_word_group_change.*

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
class WordListFragment : Fragment(), WordListContract.View {

    private lateinit var groupId: String
    var wordListAdapter: WordListAdapter? = null
    lateinit var mPresenter: WordListContract.Presenter

    companion object {
        fun createInstance(groupId: String): WordListFragment {
            val fragment = WordListFragment()
            fragment.groupId = groupId
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word_group_change, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        word_group_change_recycler_view.layoutManager = LinearLayoutManager(context)
        WordListPresenter(Injection.provideWordGroupRepository(), this)
    }

    override fun setWordList(wordList: List<TranslatedWord>) {
        if(wordListAdapter == null) {
            wordListAdapter = WordListAdapter(ArrayList(wordList), layoutInflater, object: WordListAdapterListener {
                override fun onRemoveWord(wordId: String) {
                    mPresenter.onRemoveWord(wordId, groupId)
                }

                override fun onEditWord(wordId: String, newWord: TranslatedWord) {
                    mPresenter.onEditWord(wordId, newWord, groupId)
                }
            })
            word_group_change_recycler_view.adapter = wordListAdapter
        }
        wordListAdapter?.setData(ArrayList(wordList))
    }

    override fun setPresenter(presenter: WordListContract.Presenter) {
        mPresenter = presenter

        mPresenter.loadWordList(groupId)
    }
}
