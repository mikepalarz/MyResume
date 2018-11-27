package com.palarz.mike.myresume.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.adapter.TopicsAdapter
import com.palarz.mike.myresume.ui.glide.GlideApp
import kotlinx.android.synthetic.main.activity_main.*

private val topics = setOf("Skills", "Projects", "Experience", "Education")

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var topicsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        topicsAdapter = TopicsAdapter(topics)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_topics).apply{
            setHasFixedSize(true)
            layoutManager = manager
            adapter = topicsAdapter
        }

        // TODO: Do you really need to use Glide? Think about this.
        GlideApp.with(this)
            .load(R.drawable.headshot)
            .into(headshot)
    }
}
