package com.englishwords

import com.englishwords.data.source.WordGroupRepository
import com.englishwords.data.source.local.WordGroupLocalDataSource
import com.englishwords.data.source.remote.WordGroupRemoteDataSource

object Injection {

    fun provideWordGroupRepository() : WordGroupRepository {
        return WordGroupRepository(WordGroupLocalDataSource, WordGroupRemoteDataSource)
    }
}