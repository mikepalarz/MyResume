package com.palarz.mike.myresume.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.model.Section
import com.palarz.mike.myresume.ui.model.Skills
import kotlinx.android.synthetic.main.list_item_section.view.*
import kotlinx.android.synthetic.main.list_item_skills.view.*
import kotlinx.android.synthetic.main.list_item_skills_headers.view.*

// Other view types to come
private const val VIEWTYPE_GENERIC = 0  // You likely can remove this at some point
private const val VIEWTYPE_SKILL = 1

class SectionAdapter(private val sections: Set<Section>, val context: Context) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>(){

    open class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val section = view.section
    }

    class SkillViewHolder(view: View) : SectionAdapter.SectionViewHolder(view) {
        val skillsSection = view.skills_section
        val skills = view.recyclerview_skills
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder = when(viewType) {
        VIEWTYPE_SKILL -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            SkillViewHolder(listItem)
        }

        else -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_section, parent, false)
            SectionViewHolder(listItem)
        }
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEWTYPE_SKILL -> {
                val skill: Skills = sections.elementAt(position) as Skills
                (holder as SkillViewHolder).skillsSection.text = skill.section
                val skillHeaderAdapter = SkillHeaderAdapter(Skills.headers)
                val linearLayoutManager = LinearLayoutManager(context)
                (holder as SkillViewHolder).skills.apply {
                    setHasFixedSize(true)
                    layoutManager = linearLayoutManager
                    adapter = skillHeaderAdapter
                }

            }
            else -> {
                holder.section.text = sections.elementAt(position).section
            }
        }

    }

    override fun getItemCount(): Int {
        // For the time being, we will return one so that we can focus on the Skills section
//        return sections.size
        return 1
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> VIEWTYPE_SKILL
        else -> VIEWTYPE_GENERIC
    }

}

class SkillHeaderAdapter(private val headers: Set<String>) : RecyclerView.Adapter<SkillHeaderAdapter.SkillHeaderViewHolder>() {

    class SkillHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val skillHeader = view.skills_section_header
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillHeaderAdapter.SkillHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_headers, parent, false)
        return SkillHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: SkillHeaderAdapter.SkillHeaderViewHolder, position: Int) {
        holder.skillHeader.text = headers.elementAt(position)
    }
}