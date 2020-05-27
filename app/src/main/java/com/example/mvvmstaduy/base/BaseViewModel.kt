package com.example.mvvmstaduy.base

import androidx.lifecycle.ViewModel
import com.example.mvvmstaduy.injection.component.DaggerViewModelInjector
import com.example.mvvmstaduy.injection.component.ViewModelInjector
import com.example.mvvmstaduy.network.NetWorkModule
import com.example.mvvmstaduy.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetWorkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        TODO("Not yet implemented")
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}