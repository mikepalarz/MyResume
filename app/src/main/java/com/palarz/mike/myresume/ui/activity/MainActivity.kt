package com.palarz.mike.myresume.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.fragment.ContactMeDialogFragment
import com.palarz.mike.myresume.ui.fragment.SectionsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

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

        findViewById<ImageView>(R.id.headshot).setOnClickListener {
            val contactMe = ContactMeDialogFragment()
            contactMe.show(supportFragmentManager, "")
        }

    }
}
