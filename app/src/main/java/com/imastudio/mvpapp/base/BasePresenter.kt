package com.imastudio.mvpapp.base

interface BasePresenter<T:BaseView>{
    fun onAttach(view:T)
    fun onDettach()
}