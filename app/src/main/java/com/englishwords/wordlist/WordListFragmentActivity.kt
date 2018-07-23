package com.englishwords.wordlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.englishwords.R
import com.englishwords.addFragmentToActivity

class WordListFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list_fragment)

        var wordListFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
        if(wordListFragment == null) {
            val groupId = intent.getStringExtra(resources.getString(R.string.EXTRA_GROUP_ID))
            wordListFragment = WordListFragment.createInstance(groupId)
            addFragmentToActivity(supportFragmentManager, wordListFragment, R.id.content_frame)
        }
    }
}
