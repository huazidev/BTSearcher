package com.huazidev.btsearcher.ui

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.View
import android.widget.TextView
import butterknife.ButterKnife
import com.huazidev.btsearcher.R
import com.huazidev.btsearcher.util.ClipboardHelper
import com.huazidev.btsearcher.util.ToastUtils
import com.huazidev.btsearcher.util.bindView

/**
 * @author hua on 2018/4/1.
 */
class ActionMenuDialog(context: Context, private val magnetText: String) : BottomSheetDialog(context) {

    private val copy: TextView by bindView(R.id.copy)
    private val xunlei: TextView by bindView(R.id.xunlei)
    private val download: TextView by bindView(R.id.download)

    init {
        setContentView(View.inflate(context, R.layout.dialog_action_menu, null))
        ButterKnife.bind(this, this)

        copy.setOnClickListener {
            ClipboardHelper.setText(magnetText)
            ToastUtils.longT("copy success")
            dismiss()
        }

        xunlei.setOnClickListener {

        }

        download.setOnClickListener {

        }
    }

}