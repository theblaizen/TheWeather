package com.example.theweather.util


open class RxStreamState<out T>(
    val isProcessing: Boolean = false,
    val isSuccessful: Boolean = false,
    val data: T? = null,
    val error: Throwable? = null
) {
    companion object State {
        fun <T> processing(): RxStreamState<T> = RxStreamState(isProcessing = true)
        fun <T> onError(err: Throwable): RxStreamState<T> = RxStreamState(isSuccessful = false, error = err)
        fun <T> onData(data: T): RxStreamState<T> = RxStreamState(isSuccessful = true, data = data)
    }
}