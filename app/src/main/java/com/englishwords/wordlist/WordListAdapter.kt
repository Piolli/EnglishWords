package com.englishwords.wordlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.swipe.SwipeLayout
import com.englishwords.R
import com.englishwords.data.TranslatedWord
import kotlinx.android.synthetic.main.change_word_item.view.*
import kotlinx.android.synthetic.main.change_word_item_swipe.view.*

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
class WordListAdapter(
        private val data: ArrayList<TranslatedWord>,
        private val layoutInflater: LayoutInflater,
        private val callbacks: WordListAdapterListener
)
    : RecyclerView.Adapter<WordListAdapter.TranslatedWordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslatedWordViewHolder {
        val view = layoutInflater.inflate(R.layout.change_word_item_swipe, parent, false)
        view.swipe_layout.showMode = SwipeLayout.ShowMode.LayDown
        view.swipe_layout.addDrag(SwipeLayout.DragEdge.Left, view.bottom_wrapper)

        return TranslatedWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: TranslatedWordViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, position)
        }
    }

    fun setData(wordList: ArrayList<TranslatedWord>) {
        data.clear()
        data.addAll(wordList)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = data[position]

    override fun getItemCount() = data.size

    inner class TranslatedWordViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: TranslatedWord, position: Int) {
            itemView.change_group_item_base_word.text = word.word
            itemView.change_group_item_translate_word2.text = word.translate
            itemView.change_group_item_priority.text = word.priority.toString()
            // Swipe menu item
            itemView.change_group_swipe_menu_delete.setOnClickListener {
                TODO("ERROR MAYBE THIS")
                callbacks.onRemoveWord(word.id)
//                data.remove(word)
//                notifyItemRemoved(position)
            }
            itemView.change_group_swipe_menu_edit.setOnClickListener {
                TODO("Add dialog for edit word")
                callbacks.onEditWord(word.id, TranslatedWord())
                notifyItemChanged(position)
            }
        }
    }
}