package com.github.judrummer.kithubmvp.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by judrummer on 26/3/2560.
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)