package com.palarz.mike.myresume.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.fragment.SectionsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsFragment = SectionsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_fragment_container, sectionsFragment)
            .commit()

    }
}
