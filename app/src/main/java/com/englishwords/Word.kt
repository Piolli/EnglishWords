package com.englishwords

import java.util.*

/**
 * 31.06.2018
 * version 1.0
 */

interface Word {
    val word: String?
    val translate: String?
    val wordLanguage: String?
    val priority: Int
    val id: String
}