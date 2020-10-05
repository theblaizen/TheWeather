package com.example.theweather.util.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeBy(helper: CompositeDisposable) {
    helper.add(this)
}