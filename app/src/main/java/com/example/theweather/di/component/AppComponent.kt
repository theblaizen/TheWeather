package com.example.theweather.di.component

import android.content.Context
import com.example.theweather.di.module.AppModule
import com.example.theweather.di.module.LocalStorageModule
import com.example.theweather.di.module.NetworkingModule
import com.example.theweather.di.module.UtilModule
import com.example.theweather.domain.usecase.local_storage.LocalStorageGateway
import com.example.theweather.util.Const
import com.example.theweather.util.service.UiUtil
import dagger.Component
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkingModule::class,
        LocalStorageModule::class,
        UtilModule::class
    ]
)
interface AppComponent {
    fun provideContext(): Context

    @Named(Const.MAIN_THREAD_SCHEDULER)
    fun provideMainThreadScheduler(): Scheduler

    fun provideRetrofit(): Retrofit

    fun provideLocalStorage(): LocalStorageGateway

    fun provideUiUtil(): UiUtil
}