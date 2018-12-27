package com.palarz.mike.myresume.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.adapter.FullProjectsHeaderAdapter
import kotlinx.android.synthetic.main.fragment_all_projects.view.*

class AllProjectsFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: LinearLayoutManager
    private lateinit var allProjectsAdapter: FullProjectsHeaderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_projects, container, false)

        manager = LinearLayoutManager(activity)
        allProjectsAdapter = FullProjectsHeaderAdapter(activity!!)
        recyclerView = view.rv_all_projects.apply {
            layoutManager = manager
            adapter = allProjectsAdapter
        }


        return view
    }
}