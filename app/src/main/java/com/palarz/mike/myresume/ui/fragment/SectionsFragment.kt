package com.palarz.mike.myresume.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.extensions.setBrowserClickListener
import com.palarz.mike.myresume.ui.adapter.SectionAdapter
import com.palarz.mike.myresume.model.Education
import com.palarz.mike.myresume.model.Experience
import com.palarz.mike.myresume.model.Projects
import com.palarz.mike.myresume.model.Skills
import kotlinx.android.synthetic.main.fragment_sections.view.*


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
    private lateinit var ivGithubLogo : ImageView
    private lateinit var ivTwitterLogo : ImageView
    private lateinit var ivLinkedInLogo : ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_sections, container, false)

        manager = LinearLayoutManager(activity)
        topicsAdapter = SectionAdapter(sections, activity!!)

        recyclerView = view.rv_sections.apply{
            layoutManager = manager
            adapter = topicsAdapter
        }

        ivGithubLogo = view.iv_github_logo_social.apply {
            setBrowserClickListener(getString(R.string.github_link), context)
        }

        ivTwitterLogo = view.iv_twitter_logo_social.apply {
            setBrowserClickListener(getString(R.string.twitter_link), context)
        }

        ivLinkedInLogo = view.iv_linkedin_logo_social.apply {
            setBrowserClickListener(getString(R.string.linkedin_link), context)
        }

        return view
    }

}