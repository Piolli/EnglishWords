package com.englishwords

import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.makeramen.dragsortadapter.DragSortAdapter
import io.realm.OrderedRealmCollection
import io.realm.RealmList
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.word_group_item.view.*


class WordGroupAdapter(data: OrderedRealmCollection<WordGroup>, val groupListener: OnGroupListener, autoUpdate: Boolean, updateOnModification: Boolean)
    : RealmRecyclerViewAdapter<WordGroup, WordGroupAdapter.WordGroupHolder>(data, autoUpdate, updateOnModification) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordGroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_group_item, parent, false)
        val holder = WordGroupHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: WordGroupHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class WordGroupHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(wordGroup: WordGroup) {
            itemView.word_group_name.text = wordGroup.groupName
            itemView.word_group_count_words.text = wordGroup.words.size.toString()
            itemView.setOnClickListener {
                groupListener.onClickGroup(wordGroup.id)
            }
        }
    }

    /**
     * Listener for activity that create adapter
     */
    interface OnGroupListener {
        /**
         * Perform when recycler item clicked
         */
        fun onClickGroup(groupId: String)
    }
}