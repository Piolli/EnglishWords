package com.englishwords.utils

import android.content.Context

/**
 * ImageUtils
 * Using for images
 * Created by alexandr on 01.02.18.
 * version 1.0
 */

object ImageUtils {
    /**
     * Convert density-independent pixels [dp] to pixels (px)
     */
    fun dpToPx(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        val padding_in_px = (dp * scale + 0.5f).toInt()
        return padding_in_px
    }
}