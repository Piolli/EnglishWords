package com.englishwords

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.view.*
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.englishwords.utils.DialogFactory
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.android.synthetic.main.activity_word_list.*
import java.lang.ref.SoftReference

/**
 * List of words
 * 31.06.2018
 * version 1.0
 */
class WordListActivity : AppCompatActivity() {

    lateinit var teacherSystem: TeacherSystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        setUpTeacherSystem()

        val adapter = WordsStackAdapter(this, R.layout.stack_item, teacherSystem.getSortedWords().toMutableList())
        swipe_card_view.adapter = adapter

        swipe_card_view.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                adapter.objects.removeAt(0)
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(p0: Any?) {
                if(p0 is Word) {
                    teacherSystem.onRightAnswer(p0.id)
                }
            }

            override fun onRightCardExit(p0: Any?) {
                if(p0 is Word) {
                    teacherSystem.onWrongAnswer(p0.id)
                }
            }

            override fun onAdapterAboutToEmpty(p0: Int) {
//                Toast.makeText(this@WordListActivity, "onAdapterAboutToEmpty: $p0", Toast.LENGTH_LONG).show()
            }

            override fun onScroll(p0: Float) {

            }
        })

        swipe_card_view.setOnItemClickListener { i, any ->
            Toast.makeText(this@WordListActivity, "priority: ${(any as Word).priority}", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.word_list_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_word_group_change -> {
                startActivityWithString(ChangeGroupActivity::class.java, getString(R.string.EXTRA_GROUP_ID), teacherSystem.groupId)
            }
            R.id.menu_word_group_priority_to_default -> {
                teacherSystem.toDefaultWordsThisGroup()
            }
            R.id.menu_word_group_delete -> {
                DialogFactory.dialog2(this, "Удалить группу слов?", "Да", "Отмена") {
                    if(it == DialogFactory.ANSWER.POSITIVE) {
                        teacherSystem.deleteThisGroup()
                        finish()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpTeacherSystem() {
        // Get group id from intent and init teacher system
        val groupId = intent.getStringExtra(getString(R.string.EXTRA_GROUP_ID)).toString()
        teacherSystem = TeacherSystem(WordRepositoryImpl(applicationContext), groupId)
    }

    inner class WordsStackAdapter(context: Context?, val resource: Int, var objects: MutableList<Word>) :
            ArrayAdapter<Word>(context, resource, objects) {

        lateinit var translateTextViewRef: SoftReference<TextView>
        // The first position of adapter's item
        var firstPosition = 0

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // Init stack view
            var convert: View? = convertView
            if(convertView == null)
                convert = layoutInflater.inflate(resource, parent, false)

            val wordView = convert?.findViewById<TextView>(R.id.word_stack_card_view)
            val translateView = convert?.findViewById<AppCompatTextView>(R.id.translate_stack_card_view)

            val wordItem = objects[position]

            // Persist ref for clear wildcard
            if(position == firstPosition) {
                translateTextViewRef = SoftReference(translateView!!)
            }

            wordView?.text = wordItem.word

            // Set wildcard for translate text view
            translateView?.text = getWildCardString(wordItem)

            return convert!!
        }

        /**
         * Return string with [wordItem] translate length consist of '*' symbols
         */
        private fun getWildCardString(wordItem: Word): StringBuilder {
            val strBuilder = StringBuilder()
            for (i in 0 until wordItem.translate!!.length) {
                strBuilder.append("*")
            }
            return strBuilder
        }

        override fun notifyDataSetChanged() {
            super.notifyDataSetChanged()
            if(objects.size > 0)
                translateTextViewRef.get()?.text = objects[firstPosition].translate
            translateTextViewRef.clear()
        }
    }

}
