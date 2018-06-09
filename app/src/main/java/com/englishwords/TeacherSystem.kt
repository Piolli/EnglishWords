package com.englishwords

import android.content.Context


/**
 * Respond for save right and
 * wrong translate answers
 * 06.06.2018
 * version 1.0
 */
class TeacherSystem(private val repository: WordRepository) {
    /**
     * Get sorted word by descending
     */
    fun getSortedWords(): Array<Word> {
        val words = repository.getWords()
        words.sortWith(Comparator { o1, o2 ->
            o2.priority.compareTo(o1.priority)
        })
        return words
    }

    fun onRightAnswer(wordId: String) {
        repository.decrementPriority(wordId)
    }

    fun onWrongAnswer(wordId: String) {
        repository.incrementPriority(wordId)
    }
}