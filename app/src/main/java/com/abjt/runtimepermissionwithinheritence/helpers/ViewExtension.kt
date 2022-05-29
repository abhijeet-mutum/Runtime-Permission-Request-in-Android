package com.abjt.runtimepermissionwithinheritence.helpers

import android.view.View

fun View.setOnClick(listener: () -> Unit) {
    this.setOnClickListener {
        listener()
    }
}