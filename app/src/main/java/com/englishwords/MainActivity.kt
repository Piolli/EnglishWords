package com.englishwords

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.realm.RealmList


class MainActivity : AppCompatActivity() {

    lateinit var adapter: WordGroupAdapter
    lateinit var repositoryImpl: WordRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositoryImpl = WordRepositoryImpl(this)

        val words = repositoryImpl.getWordGroupsRealmResult()

        val listener = object : WordGroupAdapter.OnGroupListener {
            override fun onClickGroup(groupId: String) = openGroup(groupId)
        }

        adapter = WordGroupAdapter(words, listener, true, true)
        word_group_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        word_group_recycler_view.setHasFixedSize(true)


        word_group_recycler_view.adapter = adapter


//        val touchHelperCallback = TouchHelperCallback()
//        val touchHelper = ItemTouchHelper(touchHelperCallback)
//        touchHelper.attachToRecyclerView(word_group_recycler_view)

    }

    fun openGroup(groupId: String) {
        val intent = Intent(this, WordListActivity::class.java)
        intent.putExtra(getString(R.string.EXTRA_GROUP_ID), groupId)
        startActivity(intent)
    }

    private inner class TouchHelperCallback internal constructor() : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//            adapter.onMove(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.data?.removeAt(viewHolder.adapterPosition)
        }

        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }
    }
}
