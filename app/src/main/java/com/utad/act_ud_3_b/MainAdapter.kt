package com.utad.act_ud_3_b

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MainAdapter(private val mDataSet: kotlin.collections.List<User>, var onCLick: (User) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_block, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        holder.bindItems(data)

        holder.itemView.setOnClickListener {
            onCLick(data)
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val mytexto = v.findViewById<TextView>(R.id.mytexto)
        val imagenPicaso = v.findViewById<ImageView>(R.id.myImg)

        fun bindItems(data: User) {
            Picasso.get().load(data.image).into(imagenPicaso)
            Log.i("pepa",data.image.toString())
            mytexto.text = data.name + "\n" + data.surname
        }
    }


}