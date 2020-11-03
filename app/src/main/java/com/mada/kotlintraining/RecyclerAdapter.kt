package com.mada.kotlintraining

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mada.kotlintraining.models.BeerFromPlugin

class RecyclerAdapter(val context: Context?, private val lister: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var beerList = listOf<BeerFromPlugin>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {

        holder.beerTitle.text = beerList.get(position).get(position).name
        holder.tagLine.text = beerList.get(position).get(position).tagline
        holder.abv.text = beerList.get(position).get(position).abv.toString() + " %"
        //holder.brewers_tips.text = beerList.get(position).get(position).brewers_tips

        Glide.with(context).load(beerList.get(position).get(position).image_url)
            .apply(RequestOptions().centerCrop())
            .apply(RequestOptions().centerInside())
            .into(holder.beerImage)
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    fun setBeerListItems(bList: List<BeerFromPlugin>) {
        this.beerList = bList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!),
        View.OnClickListener
    {

        val beerTitle: TextView = itemView!!.findViewById(R.id.text_view_1)
        val tagLine: TextView = itemView!!.findViewById(R.id.text_view_2)
        val abv: TextView = itemView!!.findViewById(R.id.text_view_3)
        //val brewers_tips: TextView = itemView!!.findViewById(R.id.text_view_4)
        val beerImage: ImageView = itemView!!.findViewById(R.id.image_view)

        init {
            itemView?.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                lister.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}