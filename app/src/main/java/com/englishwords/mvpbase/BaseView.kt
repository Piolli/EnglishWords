package com.englishwords.mvpbase

interface BaseView<T> {

    fun setPresenter(presenter: T)

}