package com.example.mvvmtest.dagger.component

import com.example.mvvmtest.adapter.ChatAdapter
import com.example.mvvmtest.adapter.MatchAdapter
import com.example.mvvmtest.dagger.module.AppModule
import com.example.mvvmtest.dagger.module.NetworkModule
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.network.ChatCall
import com.example.mvvmtest.network.RetrofitCall
import com.example.mvvmtest.repository.ChatRepository
import com.example.mvvmtest.repository.DiscoverRepository
import com.example.mvvmtest.repository.MainActivityRepository
import com.example.mvvmtest.repository.MatchRepository
import com.example.mvvmtest.ui.activity.ChatActivity
import com.example.mvvmtest.ui.fragment.DiscoverFragment
import com.example.mvvmtest.viewmodel.ChatViewModel
import com.example.mvvmtest.viewmodel.DiscoverViewModel
import com.example.mvvmtest.viewmodel.MainActivityViewModel
import com.example.mvvmtest.viewmodel.MatchViewModel

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: ChatActivity)

    fun inject(discoverFragment: DiscoverFragment)

    fun inject(chatViewModel: ChatViewModel)

    fun inject(matchViewModel: MatchViewModel)

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(discoverViewModel: DiscoverViewModel)

    fun inject(flechPreferences: FlechPreferences)

    fun inject(retrofitCall: RetrofitCall)

    fun inject(chatCall: ChatCall)

    fun inject(chatRepository: ChatRepository)

    fun inject(mainActivityRepository: MainActivityRepository)

    fun inject(discoverRepository: DiscoverRepository)

    fun inject(matchRepository: MatchRepository)

    fun inject(chatAdapter: ChatAdapter)

    fun inject(matchAdapter: MatchAdapter)

    fun inject(appModule: AppModule)

}
