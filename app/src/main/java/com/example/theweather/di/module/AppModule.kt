package com.example.theweather.di.module

import android.content.Context
import com.example.theweather.util.Const.MAIN_THREAD_SCHEDULER
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()
}