package com.example.theweather.presentation.base

import io.reactivex.disposables.CompositeDisposable


abstract class BaseRxPresenter<V : BaseView> : BasePresenter<V>() {
    protected val bag = CompositeDisposable()

    override fun detachView(view: V) {
        super.detachView(view)
        bag.clear()
    }
}