package com.palarz.mike.myresume.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.ui.model.Projects
import com.palarz.mike.myresume.ui.model.Section
import com.palarz.mike.myresume.ui.model.Skills
import kotlinx.android.synthetic.main.list_item_projects.view.*
import kotlinx.android.synthetic.main.list_item_projects_bullet.view.*
import kotlinx.android.synthetic.main.list_item_projects_headers.view.*
import kotlinx.android.synthetic.main.list_item_section.view.*
import kotlinx.android.synthetic.main.list_item_skills.view.*
import kotlinx.android.synthetic.main.list_item_skills_bullet.view.*
import kotlinx.android.synthetic.main.list_item_skills_headers.view.*

// Other view types to come
private const val VIEWTYPE_GENERIC = 0  // You likely can remove this at some point
private const val VIEWTYPE_SKILLS = 1
private const val VIEWTYPE_PROJECTS = 2

class SectionAdapter(private val sections: Set<Section>, val context: Context) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>(){

    open class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSection = view.tv_section
    }

    class SkillsViewHolder(view: View) : SectionViewHolder(view) {
        val tvSkillsSection = view.tv_skills_section
        val rvSkillsHeaders = view.rv_skills_headers
    }

    class ProjectsViewHolder(view: View) : SectionViewHolder(view) {
        val tvProjectsSection = view.tv_projects_section
        val rvProjectsHeaders = view.rv_projects_headers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder = when(viewType) {
        VIEWTYPE_SKILLS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            SkillsViewHolder(listItem)
        }

        VIEWTYPE_PROJECTS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects, parent, false)
            ProjectsViewHolder(listItem)
        }

        else -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_section, parent, false)
            SectionViewHolder(listItem)
        }
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEWTYPE_SKILLS -> {
                val skill = sections.elementAt(position) as Skills
                (holder as SkillsViewHolder).tvSkillsSection.text = skill.section
                val skillsHeadersAdapter = SkillsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                (holder as SkillsViewHolder).rvSkillsHeaders.apply {
                    setHasFixedSize(true)
                    layoutManager = linearLayoutManager
                    adapter = skillsHeadersAdapter
                }
            }
            VIEWTYPE_PROJECTS -> {
                val project = sections.elementAt(position) as Projects
                (holder as ProjectsViewHolder).tvProjectsSection.text = project.section
                val projectsHeadersAdapter = ProjectsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                (holder as ProjectsViewHolder).rvProjectsHeaders.apply {
                    layoutManager = linearLayoutManager
                    adapter = projectsHeadersAdapter
                }
            }
            else -> {
                holder.tvSection.text = sections.elementAt(position).section
            }
        }

    }

    override fun getItemCount(): Int {
        // For the time being, we will return one so that we can focus on the Skills tvSection
//        return sections.size
        return 2
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> VIEWTYPE_SKILLS
        1 -> VIEWTYPE_PROJECTS
        else -> VIEWTYPE_GENERIC
    }

}

class SkillsHeaderAdapter(val context: Context) : RecyclerView.Adapter<SkillsHeaderAdapter.SkillsHeaderViewHolder>() {

    private val headers = Skills.headers

    class SkillsHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSkillsHeader = view.tv_skills_header
        val rvBullets = view.rv_skills_bullets
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsHeaderAdapter.SkillsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_headers, parent, false)
        return SkillsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: SkillsHeaderAdapter.SkillsHeaderViewHolder, position: Int) {
        holder.tvSkillsHeader.text = headers.elementAt(position)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val skillBulletAdapter = SkillsBulletAdapter(Skills.bullets.elementAt(position))
        holder.rvBullets.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = skillBulletAdapter
        }
    }
}

class SkillsBulletAdapter(private val bullets: Set<String>) : RecyclerView.Adapter<SkillsBulletAdapter.SkillsBulletViewHolder>() {

    class SkillsBulletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBullet = view.tv_skills_bullet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_bullet, parent, false)
        return SkillsBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: SkillsBulletViewHolder, position: Int) {
        holder.tvBullet.text = "\u2022 ${bullets.elementAt(position)}"
    }
}

class ProjectsHeaderAdapter(val context: Context) : RecyclerView.Adapter<ProjectsHeaderAdapter.ProjectsHeaderViewHolder>() {

    private val headers = Projects.headers
    private val dates = Projects.dates

    class ProjectsHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProjectsHeader = view.tv_projects_header
        val tvProjectsDate = view.tv_projects_date
        val rvBullets = view.rv_projects_bullets
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects_headers, parent, false)
        return ProjectsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ProjectsHeaderViewHolder, position: Int) {
        holder.tvProjectsHeader.text = headers.elementAt(position)
        holder.tvProjectsDate.text = dates.elementAt(position)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val projectBulletAdapter = ProjectsBulletAdapter(Projects.bullets.elementAt(position))
        holder.rvBullets.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = projectBulletAdapter
        }
    }
}

class ProjectsBulletAdapter(private val bullets: Set<String>) : RecyclerView.Adapter<ProjectsBulletAdapter.ProjectsBulletViewHolder>() {

    class ProjectsBulletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBullet = view.tv_projects_bullet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects_bullet, parent, false)
        return ProjectsBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: ProjectsBulletViewHolder, position: Int) {
        holder.tvBullet.text = "\u2022 ${bullets.elementAt(position)}"
    }
}