package com.example.darli.uemanews.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.darli.uemanews.Interface.clickListener
import com.example.darli.uemanews.Base.RSS
import com.example.darli.uemanews.R

/**
 * Created by darli on 2018-07-04.
 */

class Adapter(itemView: android.view.View): android.support.v7.widget.RecyclerView.ViewHolder(itemView), android.view.View.OnClickListener, android.view.View.OnLongClickListener {

    var txtTitle: android.widget.TextView
    var txtPubdate: android.widget.TextView
    var txtContent: android.widget.TextView
    private var itemClickListener : clickListener?=null
    init {
        txtTitle = itemView.findViewById(R.id.txtTitle) as android.widget.TextView
        txtPubdate = itemView.findViewById(R.id.txtPubdate) as android.widget.TextView
        txtContent = itemView.findViewById(R.id.txtContent) as android.widget.TextView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setclickListener(itemClickListener: clickListener){
        this.itemClickListener = itemClickListener
    }
    override fun onClick(p0: android.view.View?) {
            itemClickListener!!.onClick(p0,adapterPosition,false)
    }

    override fun onLongClick(p0: android.view.View?): Boolean {
        itemClickListener!!.onClick(p0,adapterPosition,true)
        return true
    }


}


class FeedAdapter(private val rssObject: RSS,private val mContext: android.content.Context): android.support.v7.widget.RecyclerView.Adapter<Adapter>(){

    private val inflater: android.view.LayoutInflater

    init {
        inflater = android.view.LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup?, viewType: Int): Adapter {
        val itemView = inflater.inflate(R.layout.row,parent,false)
        return Adapter(itemView)

    }

    override fun getItemCount(): Int {
        return rssObject.items.size

    }

    override fun onBindViewHolder(holder: Adapter?, position: Int) {
        holder?.txtTitle?.text = rssObject.items[position].title
        holder?.txtContent?.text = rssObject.items[position].content
        holder?.txtPubdate?.text = rssObject.items[position].pubDate


        holder?.setClickListener(clickListener { view, position, isLongClick ->

            if(!isLongClick){

                val browserIntent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(rssObject.items[position].link))
                browserIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(browserIntent)
            }


        })

    }


}
