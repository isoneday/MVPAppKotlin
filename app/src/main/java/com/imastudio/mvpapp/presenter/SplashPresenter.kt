package com.imastudio.mvpapp.presenter

import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.imastudio.customerapp.helper.SessionManager
import com.imastudio.mvpapp.MainActivity
import com.imastudio.mvpapp.base.BasePresenter
import com.imastudio.mvpapp.view.activity.AuthActivity


class SplashPresenter(var splashView: SplashContract.View? = null) :
    BasePresenter<SplashContract.View>, SplashContract.Presenter {

    override fun onAttach(view: SplashContract.View) {
        splashView = view
    }

    override fun onDettach() {
        splashView = null
    }

    override fun delaySplash(
        timer: Long,
        lottie1: LottieAnimationView,
        session: SessionManager
    ) {
        lottie1.setAnimation("motorcycle.json")
        lottie1.loop(true)

        lottie1.playAnimation()
        Handler().postDelayed(Runnable {
           if (session.isLogin){
               splashView?.pindahHalaman(MainActivity::class.java)
               splashView?.welcomeMsg()

           }else{
               splashView?.pindahHalaman(AuthActivity::class.java)
               splashView?.welcomeMsg()
           }


        }, timer)
    }
}