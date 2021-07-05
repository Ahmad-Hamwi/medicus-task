package com.ahmadhamwi.medicus_task.presentation.util

import android.app.Activity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibility")
fun visibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:existence")
fun existence(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("app:applySystemBarsInsetOnPadding")
fun applySystemBarsInsetOnPadding(view: View, apply: Boolean) {
    if (!apply) return
    ViewCompat.setOnApplyWindowInsetsListener(view) { _, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updatePadding(bottom = insets.bottom, top = insets.top)
        windowInsets
    }
}

@BindingAdapter("app:setDecorFitSystemWindow")
fun setDecorFitSystemWindow(view: View, apply: Boolean) {
    if (!apply) return
    if (view.context is Activity) {
        WindowCompat.setDecorFitsSystemWindows((view.context as Activity).window, false)
    }
}