package com.englishwords.wordgroup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.englishwords.Injection
import kotlinx.android.synthetic.main.activity_main.*
import com.englishwords.R
import com.englishwords.data.WordGroup
import com.englishwords.wordstack.WordStackActivity


class WordGroupListActivity : AppCompatActivity(), WordGroupListContract.View {
    lateinit var mPresenter: WordGroupListContract.Presenter

    private var wordGroupListAdapter: WordGroupListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordGroupListPresenter(Injection.provideWordGroupRepository(), this)


        word_group_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        word_group_recycler_view.setHasFixedSize(true)
        word_group_recycler_view.adapter = wordGroupListAdapter

    }

    override fun setWordGroupList(wordGroupList: List<WordGroup>) {
        if(wordGroupListAdapter == null) {
            wordGroupListAdapter = WordGroupListAdapter(ArrayList(wordGroupList), object: WordGroupListAdapter.OnGroupListener {
                override fun onClickGroup(groupId: String) {
                    openGroupActivity(groupId)
                }

                override fun onDeleteGroup(groupId: String) {

                }
            })
        }
        else {
            wordGroupListAdapter?.setWordGroupList(ArrayList(wordGroupList))
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadWordGroupList()
    }

    fun openGroupActivity(groupId: String) {
        val intent = Intent(this, WordStackActivity::class.java)
        intent.putExtra(this.getString(R.string.EXTRA_GROUP_ID), groupId)
        startActivity(intent)
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }

    override fun setPresenter(presenter: WordGroupListContract.Presenter) {
        mPresenter = presenter
        mPresenter.loadWordGroupList()
    }

}
