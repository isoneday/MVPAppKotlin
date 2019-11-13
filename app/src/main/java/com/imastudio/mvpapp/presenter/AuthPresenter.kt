package com.imastudio.mvpapp.presenter

import com.imastudio.mvpapp.base.BasePresenter
import com.imastudio.mvpapp.model.ResponseAuth
import com.imastudio.mvpapp.network.InitRetrofit
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
            strJenkel,email, strLevel.toString(),password).enqueue(
            object :Callback<ResponseAuth>{
                override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                authView?.showError(t.localizedMessage.toString())
                    authView?.hideLoading()
                }

                override fun onResponse(
                    call: Call<ResponseAuth>,
                    response: Response<ResponseAuth>
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

    override fun prosesLogin(
        username: String,
        password: String,
        strLevel: String?
    ) {
        authView?.showLoading()
        InitRetrofit.getInstance().loginuser(username, strLevel, password).enqueue(
            object : Callback<ResponseAuth> {
                override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                    authView?.showError(t.printStackTrace().toString())
                    authView?.hideLoading()
                }

                override fun onResponse(
                    call: Call<ResponseAuth>,
                    response: Response<ResponseAuth>
                ) {
                    authView?.hideLoading()
                    if (response.isSuccessful) {
                        var result = response.body()?.result
                        var msg = response.body()?.msg
                        var dataLogin = response.body()?.user

                        if (result.equals("1")) {
                            authView?.showMsg(msg)
                            authView?.hideDialog()
                            authView?.pindahHalaman(dataLogin)

                        } else {
                            authView?.showMsg(msg)

                        }
                    }

                }
            }
        )
    }
}