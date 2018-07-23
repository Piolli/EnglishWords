package com.englishwords.wordgroup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.englishwords.R
import com.englishwords.data.WordGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.word_group_item.view.*


class WordGroupListAdapter(
        private val data: ArrayList<WordGroup>,
        private val groupListener: OnGroupListener
)
    : RecyclerView.Adapter<WordGroupListAdapter.WordGroupHolder>() {

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

    fun setWordGroupList(wordGroups: ArrayList<WordGroup>) {
        data.clear()
        data.addAll(wordGroups)
        notifyDataSetChanged()
    }

    fun addWordGroupList(wordGroup: WordGroup) {
        data.add(wordGroup)
        notifyItemChanged(data.size)
    }

    private fun getItem(position: Int) = data[position]

    override fun getItemCount() = data.size

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
     * Listener for activity that create wordGroupListAdapter
     */
    interface OnGroupListener {
        /**
         * Perform when recycler item clicked
         */
        fun onClickGroup(groupId: String)

        /**
         * Perform when item deleted
         */
        fun onDeleteGroup(groupId: String)


    }
}