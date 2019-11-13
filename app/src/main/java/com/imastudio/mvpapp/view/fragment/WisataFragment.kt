package com.imastudio.mvpapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.imastudio.mvpapp.R
import com.imastudio.mvpapp.adapter.AdapterWisata
import com.imastudio.mvpapp.model.modelwisata.DataItem
import com.imastudio.mvpapp.presenter.WisataContract
import com.imastudio.mvpapp.presenter.WisataPresenter
import kotlinx.android.synthetic.main.fragment_wisata.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class WisataFragment : Fragment(), WisataContract.View {


    lateinit var presenter: WisataPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wisata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressbar.visibility = View.VISIBLE
        initPresenter()
        presenter.getDataWisata()
    }

    private fun initPresenter() {
        presenter = WisataPresenter(this)
    }

    override fun showMsg(msg: String?) {
        msg?.let { toast(it) }
    }

    override fun showError(localizedMessage: String?) {
        localizedMessage?.let { toast(it) }

    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun pindahHalaman(dataWisata: List<DataItem?>?) {
        var adapter = AdapterWisata(activity, dataWisata)
        listWisata.adapter = adapter
        listWisata.layoutManager = LinearLayoutManager(activity)

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
