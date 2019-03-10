package com.example.theweather.presentation.base

abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null

    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView(view: V) {
        if (this.view == view) {
            this.view = null
        }
    }
}