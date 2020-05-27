package com.example.mvvmstaduy.injection.component

import com.example.mvvmstaduy.network.NetWorkModule
import com.example.mvvmstaduy.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetWorkModule::class)])
interface ViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetWorkModule): Builder
    }
}