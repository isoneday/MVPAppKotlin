package com.imastudio.mvpapp.presenter

import com.imastudio.customerapp.network.InitRetrofit
import com.imastudio.mvpapp.base.BasePresenter
import com.imastudio.mvpapp.model.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthPresenter(var authView : AuthContract.View?=null) :
BasePresenter<AuthContract.View>,AuthContract.Presenter{
    override fun onAttach(view: AuthContract.View) {
    authView =view
    }

    override fun onDettach() {
    authView =null
    }

    override fun prosesRegister(
        email: String,
        name: String,
        alamat: String,
        nohp: String,
        password: String,
        strJenkel: String?,
        usia: String,
        strLevel: String?
    ) {
        authView?.showLoading()
        InitRetrofit.getInstance().registerUser(name,usia,alamat,nohp,
            strJenkel,email,strLevel,password).enqueue(
            object :Callback<ResponseRegister>{
                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                authView?.showError(t.printStackTrace().toString())
                    authView?.hideLoading()
                }

                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    authView?.hideLoading()
                    if (response.isSuccessful){
                        var result=response.body()?.result
                        var msg =response.body()?.msg
                        if (result.equals("1")){
                            authView?.showMsg(msg)
                            authView?.hideDialog()

                        }else{
                            authView?.showMsg(msg)

                        }
                    }

                }

            }
        )

    }

    override fun prosesLogin() {
    }
}