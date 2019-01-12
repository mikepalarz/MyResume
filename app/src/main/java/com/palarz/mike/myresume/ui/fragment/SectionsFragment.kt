package com.palarz.mike.myresume.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.palarz.mike.myresume.BuildConfig
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.extensions.getResumeFile
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
    private lateinit var ivAdobeLogo : ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_sections, container, false)

        manager = LinearLayoutManager(activity)
        topicsAdapter = SectionAdapter(sections, activity!!)

        recyclerView = view.rv_sections.apply{
            layoutManager = manager
            adapter = topicsAdapter
            // Had to add this since scrolling is slow within a NestedScrollView without it
            ViewCompat.setNestedScrollingEnabled(this, false)
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

        // TODO: Consider using a WebView for this instead and using an HTML file within res/raw. Why? Well, I suppose
        // just to play around with WebViews :D
        ivAdobeLogo = view.iv_adobe_acrobat_logo_social
        ivAdobeLogo.setOnClickListener {
            val pdfIntent = Intent(Intent.ACTION_VIEW)
            val file = resources.getResumeFile(context!!)
            val uri = FileProvider.getUriForFile(context!!, "${BuildConfig.APPLICATION_ID}.fileprovider", file)

            val chooserTitle = resources.getString(R.string.app_chooser_title)

            pdfIntent.apply {
                setDataAndType(uri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                val chooser = Intent.createChooser(this, chooserTitle)
                // Checking if there's an activity to handle pdfIntent
                if (pdfIntent.resolveActivity(activity?.packageManager) != null) {
                    context?.startActivity(chooser)
                }
            }
        }

        return view
    }

}