package com.palarz.mike.myresume.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.adapter.SectionAdapter
import com.palarz.mike.myresume.model.Education
import com.palarz.mike.myresume.model.Experience
import com.palarz.mike.myresume.model.Projects
import com.palarz.mike.myresume.model.Skills
import com.palarz.mike.myresume.ui.adapter.MoreButtonCallback
import kotlinx.android.synthetic.main.fragment_sections.view.*
import java.lang.ClassCastException


private val sections = setOf(
    Skills("Skills"),
    Projects("Projects"),
    Experience("Experience"),
    Education("Education")
)

class SectionsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var topicsAdapter: SectionAdapter
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var activityCallback: MoreButtonCallback

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activityCallback = context as MoreButtonCallback
        } catch (exception: ClassCastException) {
            throw ClassCastException("${context.toString()} +  must implement StepLoader")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_sections, container, false)

        manager = LinearLayoutManager(activity)
        topicsAdapter = SectionAdapter(sections, activity!!, activityCallback)

        recyclerView = view.rv_sections.apply{
            setHasFixedSize(true)
            layoutManager = manager
            adapter = topicsAdapter
        }

        return view
    }

}