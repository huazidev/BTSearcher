package com.huazidev.btsearcher.ui

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.View
import butterknife.ButterKnife
import com.huazidev.btsearcher.R

/**
 * @author hua on 2018/4/1.
 */
class ActionMenuDialog(context: Context) : BottomSheetDialog(context) {

    init {
        setContentView(View.inflate(context, R.layout.dialog_action_menu, null))
        ButterKnife.bind(this, this)
    }

}