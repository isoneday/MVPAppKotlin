package com.imastudio.mvpapp.presenter

import com.airbnb.lottie.LottieAnimationView
import com.imastudio.customerapp.helper.SessionManager
import com.imastudio.mvpapp.base.BaseView

interface SplashContract {

    interface Presenter{
        fun delaySplash(
            timer: Long,
            lottie1: LottieAnimationView,
            session: SessionManager
        )
    }

    interface View :BaseView{
        fun pindahHalaman(java: Class<*>)
        fun welcomeMsg()
    }
}