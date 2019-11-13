package com.imastudio.mvpapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.customerapp.helper.SessionManager
import com.imastudio.mvpapp.R
import com.imastudio.mvpapp.presenter.SplashContract
import com.imastudio.mvpapp.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.toast

class SplashScreenActivity : AppCompatActivity(), SplashContract.View {

    lateinit var presenter: SplashPresenter
    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        session = SessionManager(this)

        initPresenter()
        presenter.delaySplash(5000, lottie1, session)
    }

    private fun initPresenter() {
        presenter = SplashPresenter(this)
    }

    override fun pindahHalaman(java1: Class<*>) {
        startActivity(Intent(this, java1))
        finish()
    }

    override fun welcomeMsg() {
        if (session.isLogin)
            toast("selamat datang kembali ${session.getToken()} \ndi aplikasi")
        else toast("selamat datang di aplikasi")
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

}
