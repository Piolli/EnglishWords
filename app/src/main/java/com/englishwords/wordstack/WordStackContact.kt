package com.englishwords.wordstack

import com.englishwords.data.Word
import com.englishwords.mvpbase.BasePresenter
import com.englishwords.mvpbase.BaseView
import com.englishwords.utils.DialogFactory
import com.lorentzos.flingswipe.SwipeFlingAdapterView

/**
 * Created by Alexandr Kamyshev on 23/07/2018.
 */
interface WordStackContact {

    interface View : BaseView<Presenter> {

        fun setWords(words: ArrayList<Word>)

        fun removeFirstWord()

        fun showTranslate()

        fun showChangeGroupActivity(groupId: String)

        fun showDeleteWordGroupDialog(onCloseDialog: (DialogFactory.ANSWER) -> Unit)

        fun closeActivity()

    }

    interface Presenter : BasePresenter {

        fun loadWordsIntoStack()

        fun setUpTeacherSystem(groupId: String)

        fun getStackListener(): SwipeFlingAdapterView.onFlingListener

        fun getStackItemListener(): SwipeFlingAdapterView.OnItemClickListener

        fun onOptionsItemSelected(itemId: Int)

    }

}