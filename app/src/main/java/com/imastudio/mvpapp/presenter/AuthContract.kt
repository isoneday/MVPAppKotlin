package com.imastudio.mvpapp.presenter

import com.imastudio.mvpapp.base.BaseView

interface AuthContract {
    interface Presenter{
        fun prosesRegister(
            email: String,
            name: String,
            alamat: String,
            nohp: String,
            password: String,
            strJenkel: String?,
            usia: String,
            strLevel: String?
        )
        fun prosesLogin()
    }

    interface View:BaseView{
        fun showLoading()
        fun hideLoading()
        fun hideDialog()
        fun showError(toString: String)
        fun pindahHalaman()
        fun showMsg(msg: String?)
    }
}