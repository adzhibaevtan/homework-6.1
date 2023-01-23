package com.transfer.homework_61.utils

import android.app.ProgressDialog.show
import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}