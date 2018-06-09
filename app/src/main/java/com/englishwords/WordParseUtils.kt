package com.englishwords

object WordParseUtils {

    fun parseFromString(words: String, delimeterBetweenStrings: String = "\n", delimeterBetweenWords: String = " - "): Array<TranslatedWord> {
        val wordList = mutableListOf<TranslatedWord>()
        val wordIterator = words.split(delimeterBetweenStrings).iterator()
        while(wordIterator.hasNext()) {
            val word = wordIterator.next()
            if(word.isEmpty()) continue
            val wordToTranslate = word.split(delimeterBetweenWords)
            wordList.add(TranslatedWord(wordToTranslate[0], wordToTranslate[1], priority = 5))
        }

        return wordList.toTypedArray()
    }
}