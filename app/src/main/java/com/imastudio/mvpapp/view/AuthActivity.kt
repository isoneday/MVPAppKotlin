package com.imastudio.mvpapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.customerapp.helper.SessionManager
import com.imastudio.mvpapp.MainActivity
import com.imastudio.mvpapp.R
import com.imastudio.mvpapp.model.User
import com.imastudio.mvpapp.presenter.AuthContract
import com.imastudio.mvpapp.presenter.AuthPresenter
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.layout_login.view.*
import kotlinx.android.synthetic.main.layout_register.view.*
import kotlinx.android.synthetic.main.layout_register.view.regAdmin
import kotlinx.android.synthetic.main.layout_register.view.regUserbiasa
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class AuthActivity : AppCompatActivity() ,AuthContract.View, AdapterView.OnItemSelectedListener {


//    var dataJenkel = arrayOf("Laki - laki", "perempuan")
    var strJenkel :String?=null
    var strLevel :String?=null
    var progressDialog : ProgressDialog? =null
    lateinit var session :SessionManager

    lateinit var dialog : AlertDialog
    lateinit var presenter : AuthPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.imastudio.mvpapp.R.layout.activity_auth)
        progressDialog = ProgressDialog(this)
        session = SessionManager(this)

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
        var alertRegister = AlertDialog.Builder(this)
        alertRegister.setTitle("login")
        alertRegister.setMessage("Silahkan login data anda")
        var v = layoutInflater.inflate(R.layout.layout_login, null)
        alertRegister.setView(v)
        alertRegister.setPositiveButton("login",null)
        alertRegister.setNegativeButton("Cancel",null)
        dialog = alertRegister.create()
        dialog.show()

        getLevel(v)
        v.regAdmin.onClick {
            strLevel ="admin"
        }
        v.regUserbiasa.onClick {
            strLevel ="user biasa"
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).onClick {
            var username  =v.logUsername.text.toString()
            var password =v.logPass.text.toString()

            //cek kosong atau tidak
            if (TextUtils.isEmpty(username)) v.logUsername.error="username tidak boleh kosong"
            else if (TextUtils.isEmpty(password)) v.logPass.error="name tidak boleh kosong"
            else presenter.prosesLogin(username,password,strLevel.toString())
        }
    }

    private fun register() {
        var v = layoutInflater.inflate(R.layout.layout_register, null)

        var alertRegister = AlertDialog.Builder(this)
        alertRegister.setTitle("register")
        alertRegister.setMessage("Silahkan register data anda")

        alertRegister.setView(v)
        alertRegister.setPositiveButton("Register",null)
        alertRegister.setNegativeButton("Cancel",null)
         dialog = alertRegister.create()
        dialog.show()
        strJenkel = getJenkel(v)
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
            else presenter.prosesRegister(email,name,alamat,nohp,password,
                strJenkel.toString(),usia,strLevel)
        }

    }

    private fun getLevel(v: View) {
        if (v.regAdmin.isChecked) strLevel ="admin"
        else strLevel ="user biasa"
    }

    private fun getJenkel(v: View): String? {

        ArrayAdapter.createFromResource(
            this,
            R.array.jenkel,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            v.spinjenkel.adapter = adapter
            v.spinjenkel.onItemSelectedListener = this
        }
        return strJenkel
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
        toast("gagal:"+toString)
    }

    override fun pindahHalaman(dataLogin: User?) {
        session.createLoginSession(dataLogin?.username)
        session.iduser= dataLogin?.idUser.toString()
        startActivity<MainActivity>()
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
    override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        strJenkel= p0?.getItemAtPosition(p2).toString()
      }
}
