package com.imastudio.mvpapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.mvpapp.R
import com.imastudio.mvpapp.presenter.AuthContract
import com.imastudio.mvpapp.presenter.AuthPresenter
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.layout_register.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener
import org.jetbrains.anko.toast


class AuthActivity : AppCompatActivity() ,AuthContract.View{

    var dataJenkel = arrayOf("Laki - laki", "perempuan")
    var strJenkel :String?=null
    var strLevel :String?=null
    var progressDialog : ProgressDialog? =null
    lateinit var dialog : AlertDialog
    lateinit var presenter : AuthPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.imastudio.mvpapp.R.layout.activity_auth)
        progressDialog = ProgressDialog(this)
        initPresenter()
        btnRegister.onClick {
            register()
        }
        btnSignIn.onClick {
            login()
        }
    }

    private fun initPresenter() {
        presenter = AuthPresenter(this)
    }

    private fun login() {

    }

    private fun register() {

        var alertRegister = AlertDialog.Builder(this)
        alertRegister.setTitle("register")
        alertRegister.setMessage("Silahkan register data anda")
        var v = layoutInflater.inflate(R.layout.layout_register, null)
        alertRegister.setView(v)
        alertRegister.setPositiveButton("Register",null)
        alertRegister.setNegativeButton("Cancel",null)
         dialog = alertRegister.create()
        dialog.show()
        getJenkel(v)
        getLevel(v)
        v.regAdmin.onClick {
            strLevel ="admin"
        }
        v.regUserbiasa.onClick {
            strLevel ="user biasa"
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).onClick {
            var email = v.edtusername.text.toString()
            var name  =v.edtnama.text.toString()
            var nohp =v.edtnotelp.text.toString()
            var alamat =v.edtalamat.text.toString()
            var usia =v.edtusia.text.toString()
            var password =v.edtpassword.text.toString()
            var conPass =v.edtpasswordconfirm.text.toString()

            //cek kosong atau tidak
            if (TextUtils.isEmpty(email)) v.edtusername.error="username tidak boleh kosong"
            else if (TextUtils.isEmpty(name)) v.edtnama.error="name tidak boleh kosong"
            else if (TextUtils.isEmpty(nohp)) v.edtnotelp.error="nohp tidak boleh kosong"
            else if (TextUtils.isEmpty(usia)) v.edtusia.error="usia tidak boleh kosong"
            else if (TextUtils.isEmpty(alamat)) v.edtalamat.error="alamat tidak boleh kosong"
            else if (TextUtils.isEmpty(password)) v.edtpassword.error="password tidak boleh kosong"
            else if (!password.equals(conPass)) v.edtpasswordconfirm.error="confirm password tidak sama"
            else presenter.prosesRegister(email,name,alamat,nohp,password,strJenkel,usia,strLevel)
        }

    }

    private fun getLevel(v: View) {
        if (v.regAdmin.isChecked) strLevel ="admin"
        else strLevel ="user biasa"
    }

    private fun getJenkel(v: View) {
        var adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,dataJenkel)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.spinjenkel.adapter =adapter
        v.spinjenkel.onItemSelectedListener {
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                strJenkel = dataJenkel[p2]
                }

            }
        }
    }

    override fun showLoading() {
    progressDialog?.setTitle("Proses Register")
        progressDialog?.setMessage("loading . . . .")
        progressDialog?.show()
    }

    override fun hideLoading() {
    progressDialog?.dismiss()
    }

    override fun hideDialog() {
    dialog.dismiss()
    }

    override fun showError(toString: String) {
        toast("gagal:")
    }

    override fun pindahHalaman() {
    }

    override fun showMsg(msg: String?) {
        toast(msg.toString())
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
