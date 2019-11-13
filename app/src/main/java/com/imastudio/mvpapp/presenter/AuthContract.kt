package com.imastudio.mvpapp.presenter

import com.imastudio.mvpapp.base.BaseView
import com.imastudio.mvpapp.model.modelauth.User

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
        fun prosesLogin(
            username: String,
            password: String,
            strLevel: String?
        )
    }

    interface View:BaseView{
        fun showLoading()
        fun hideLoading()
        fun hideDialog()
        fun showError(toString: String)
        fun pindahHalaman(dataLogin: User?)
        fun showMsg(msg: String?)
    }
}