package com.imastudio.mvpapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.imastudio.mvpapp.R
import com.imastudio.mvpapp.helper.Helper.Companion.DATAWISATA
import com.imastudio.mvpapp.model.modelwisata.DataItem
import com.imastudio.mvpapp.view.activity.DetailWisataActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tampilanwisata.view.*
import org.jetbrains.anko.startActivity

class AdapterWisata(
    var activity: FragmentActivity?,
    var dataWisata: List<DataItem?>?
) : RecyclerView.Adapter<AdapterWisata.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AdapterWisata.MyViewHolder = MyViewHolder(
        LayoutInflater.from(activity).inflate(
            R.layout.tampilanwisata
            , parent, false
        )
    )


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: DataItem, act: FragmentActivity) {
            Picasso.get().load(item.gambar).placeholder(R.drawable.empty)
                .error(R.drawable.empty).into(itemView.itemImg)
            itemView.itemJudul.text = item.namaTempat
            itemView.itemDesk.text = item.deskripsi
            itemView.setOnClickListener {
                act.startActivity<DetailWisataActivity>(DATAWISATA to item)
            }

        }
    }

    //untuk menghitung total data yg akan di load
    override fun getItemCount(): Int = dataWisata!!.size

    override fun onBindViewHolder(holder: AdapterWisata.MyViewHolder, position: Int) =
        holder.bindItem(dataWisata?.get(position)!!,activity!!)
}