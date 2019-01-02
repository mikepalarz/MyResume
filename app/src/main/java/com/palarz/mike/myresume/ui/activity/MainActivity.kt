package com.palarz.mike.myresume.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.fragment.SectionsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    
//    TODO: Create a dialog that pops up for the on-click of your headshot, with contact options
//    TODO: Add images for your different social profiles
//    TODO: Update the content, w/ more recent projects that you've worked on

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsFragment = SectionsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container, sectionsFragment)
            .commit()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
}
