package com.github.judrummer.kithubmvp.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by judrummer on 26/3/2560.
 */

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    this.context.toast(message, length)
}
