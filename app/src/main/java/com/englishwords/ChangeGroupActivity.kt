package com.englishwords

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.daimajia.swipe.SwipeLayout
import com.englishwords.data.TranslatedWord
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_change_group.*
import kotlinx.android.synthetic.main.change_word_item.view.*
import kotlinx.android.synthetic.main.change_word_item_swipe.view.*

class ChangeGroupActivity : AppCompatActivity(), WordListAdapterCallbacks {
    lateinit var repositoryImpl: WordRepositoryImpl
    lateinit var groupId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_group)

        change_group_recycler_view.layoutManager = LinearLayoutManager(this)

        repositoryImpl = WordRepositoryImpl(this)
        groupId = intent.getStringExtra(getString(R.string.EXTRA_GROUP_ID))
        val group = repositoryImpl.getWordGroups().find { it.id == groupId }
        val words = group?.words
        change_group_recycler_view.adapter = WordListAdapter(words, true, true, this)
        change_group_name.setText(group?.groupName)
    }

    override fun onRemoveWord(wordId: String) {
        repositoryImpl.removeWord(wordId, groupId)
    }

    override fun onEditWord(wordId: String, newWord: TranslatedWord) {

    }

    inner class WordListAdapter(data: OrderedRealmCollection<TranslatedWord>?, autoUpdate: Boolean, updateOnModification: Boolean,
                                val callbacks: WordListAdapterCallbacks)
        : RealmRecyclerViewAdapter<TranslatedWord, WordListAdapter.TranslatedWordViewHolder>(data, autoUpdate, updateOnModification) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslatedWordViewHolder {
            val view = layoutInflater.inflate(R.layout.change_word_item_swipe, parent, false)
            view.swipe_layout.showMode = SwipeLayout.ShowMode.LayDown
            view.swipe_layout.addDrag(SwipeLayout.DragEdge.Left, view.bottom_wrapper)

            return TranslatedWordViewHolder(view)
        }

        override fun onBindViewHolder(holder: TranslatedWordViewHolder, position: Int) {
            getItem(position)?.let {
                holder.bind(it)
            }
        }

        inner class TranslatedWordViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            fun bind(word: TranslatedWord) {
                itemView.change_group_item_base_word.text = word.word
                itemView.change_group_item_translate_word2.text = word.translate
                itemView.change_group_item_priority.text = word.priority.toString()
                // Swipe menu item
                itemView.change_group_swipe_menu_delete.setOnClickListener {
                    callbacks.onRemoveWord(word.id)
                }
                itemView.change_group_swipe_menu_edit.setOnClickListener {
                    TODO("Add dialog for edit word")
                    callbacks.onEditWord(word.id, TranslatedWord())
                }
            }
        }
    }
}

interface WordListAdapterCallbacks {
    fun onRemoveWord(wordId: String)
    fun onEditWord(wordId: String, newWord: TranslatedWord)
}
