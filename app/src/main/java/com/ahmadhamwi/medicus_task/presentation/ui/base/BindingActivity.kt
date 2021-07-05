package com.ahmadhamwi.medicus_task.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Activity depends on Data-Binding
 */
abstract class BindingActivity<DATA_BINDING : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: DATA_BINDING

    protected fun setView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        setupDataBinding()
    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}