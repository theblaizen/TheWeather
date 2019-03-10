package com.example.theweather.di.module

import android.content.Context
import com.example.theweather.util.service.UiUtil
import com.example.theweather.util.service.UiUtilImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilModule {

    @Singleton
    @Provides
    fun provideUiUtils(context: Context): UiUtil {
        return UiUtilImpl(context)
    }
}