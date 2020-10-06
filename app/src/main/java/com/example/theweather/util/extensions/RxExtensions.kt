package com.example.theweather.util.extensions

import com.example.theweather.util.RxStreamState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeBy(helper: CompositeDisposable) {
    helper.add(this)
}

fun <T> Single<out T>.wrapWithStreamWrapper() =
    this.toObservable()
        .wrapWithStreamWrapper()

fun <T> Observable<out T>.wrapWithStreamWrapper() =
    this.map { data -> RxStreamState.onData(data) }
        .startWith(RxStreamState.processing())
        .onErrorReturn { err -> RxStreamState.onError(err) }

fun <T : RxStreamState<*>> Observable<T>.filterError() = this.filter { !it.isProcessing && !it.isSuccessful }

fun <T : RxStreamState<*>> Observable<T>.filterSuccess() = this.filter { it.isSuccessful && !it.isProcessing }

fun <R, T : RxStreamState<R>> Observable<T>.receive(): Observable<R> = this.map { it.data }

fun <T : RxStreamState<*>> Observable<T>.toss() = this.map {
    it.error ?: Throwable("Something went wrong")
}