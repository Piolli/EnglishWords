package com.englishwords

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.startActivityWithString(javaClass: Class<*>, extraId: String, data: String) {
    val intent = Intent(this, javaClass)
    intent.putExtra(extraId, data)
    this.startActivity(intent)
}