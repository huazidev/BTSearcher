package com.huazidev.btsearcher.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.huazidev.btsearcher.common.BTSearcherApplicationContext


/**
 * @author hua on 2018/4/1.
 */
object ClipboardHelper {
    private const val BT_SEARCHER = "bt_searcher"
    var manager = BTSearcherApplicationContext.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun setText(text: String) {
        manager.primaryClip = ClipData.newPlainText(BT_SEARCHER, text)
    }

    fun getText(): String? {
        val clipData = manager.primaryClip
        return if (clipData != null) {
            if (clipData.itemCount > 0) {
                manager.primaryClip.getItemAt(0).coerceToText(BTSearcherApplicationContext.context).toString()
            } else null
        } else null
    }
}