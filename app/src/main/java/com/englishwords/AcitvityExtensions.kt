package com.englishwords

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


fun AppCompatActivity.startActivityWithString(javaClass: Class<*>, extraId: String, data: String) {
    val intent = Intent(this, javaClass)
    intent.putExtra(extraId, data)
    this.startActivity(intent)
}

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 *
 */
fun AppCompatActivity.addFragmentToActivity(fragmentManager: FragmentManager,
                          fragment: Fragment, frameId: Int) {
    checkNotNull(fragmentManager)
    checkNotNull(fragment)
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}