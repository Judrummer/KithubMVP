package com.github.judrummer.kithubmvp.extension

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by judrummer on 26/3/2560.
 */

fun FragmentManager.transaction(transactionBlock: (FragmentTransaction).() -> (Unit)) {
    val transaction = beginTransaction()
    transaction.transactionBlock()
    transaction.commit()
}
