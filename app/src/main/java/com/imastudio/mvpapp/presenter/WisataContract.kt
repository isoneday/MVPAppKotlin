package com.imastudio.mvpapp.presenter

import com.imastudio.mvpapp.base.BaseView
import com.imastudio.mvpapp.model.modelwisata.DataItem

interface WisataContract {

    interface Presenter {
        fun getDataWisata()
    }

    interface View : BaseView{
        fun showMsg(msg: String?)
        fun showError(localizedMessage: String?)
        fun showLoading()
        fun hideLoading()
        fun pindahHalaman(dataWisata: List<DataItem?>?)
    }
}