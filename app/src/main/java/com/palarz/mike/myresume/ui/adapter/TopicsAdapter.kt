package com.palarz.mike.myresume.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palarz.mike.myresume.R
import kotlinx.android.synthetic.main.list_item_topic.view.*

class TopicsAdapter(private val topics: Set<String>) : RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder>(){

    class TopicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topic = view.topic
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_topic, parent, false)
        return TopicsViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.topic.text = topics.elementAt(position)
    }

    override fun getItemCount(): Int {
        return topics.size
    }
}