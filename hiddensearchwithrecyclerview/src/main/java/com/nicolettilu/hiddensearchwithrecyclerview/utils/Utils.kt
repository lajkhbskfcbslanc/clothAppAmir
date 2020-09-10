package com.nicolettilu.hiddensearchwithrecyclerview.utils

import android.content.Context

/**
 * Created by Luca Nicoletti
 * © 28/07/2018
 * All rights reserved.
 */

class Utils {
    companion object {
        /**
         * Convert a given number in DP to the corresponding number in pixel for the current device in use
         */
        fun convertDpToPixel(context: Context, dp: Float): Float {
            val metrics = context.resources.displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px).toFloat()
        }
    }
}