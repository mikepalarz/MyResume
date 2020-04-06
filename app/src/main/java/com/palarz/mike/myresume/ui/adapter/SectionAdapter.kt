package com.palarz.mike.myresume.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.extensions.*
import com.palarz.mike.myresume.model.*
import kotlinx.android.synthetic.main.list_item_education.view.*
import kotlinx.android.synthetic.main.list_item_education_headers.view.*
import kotlinx.android.synthetic.main.list_item_experience.view.*
import kotlinx.android.synthetic.main.list_item_experience_bullet.view.*
import kotlinx.android.synthetic.main.list_item_experience_companies.view.*
import kotlinx.android.synthetic.main.list_item_projects.view.*
import kotlinx.android.synthetic.main.list_item_projects_bullet.view.*
import kotlinx.android.synthetic.main.list_item_projects_headers.view.*
import kotlinx.android.synthetic.main.list_item_section.view.*
import kotlinx.android.synthetic.main.list_item_skills.view.*
import kotlinx.android.synthetic.main.list_item_skills_bullet.view.*
import kotlinx.android.synthetic.main.list_item_skills_headers.view.*
import timber.log.Timber

private const val VIEWTYPE_GENERIC = 0
private const val VIEWTYPE_SKILLS = 1
private const val VIEWTYPE_PROJECTS = 2
private const val VIEWTYPE_EXPERIENCE = 3
private const val VIEWTYPE_EDUCATION = 4

// TODO: I feel like this can be cleaned up using sealed classes:
// https://proandroiddev.com/understanding-kotlin-sealed-classes-65c0adad7015
class SectionAdapter(val context: Context) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>(){

    private val sections = setOf(Skills(), Projects(), Experience(), Education())

    open class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSection = view.tv_section
    }

    class SkillsViewHolder(view: View) : SectionViewHolder(view) {
        val tvSkillsSection = view.tv_skills_section
        val rvSkillsHeaders = view.findViewById<RecyclerView>(R.id.rv_content)
        var maxHeight = rvSkillsHeaders.measuredHeight
    }

    class ProjectsViewHolder(view: View) : SectionViewHolder(view) {
        val tvProjectsSection = view.tv_projects_section
        val rvProjectsHeaders = view.findViewById<RecyclerView>(R.id.rv_content)
        var maxHeight = rvProjectsHeaders.measuredHeight
    }

    class ExperienceViewHolder(view: View) : SectionViewHolder(view) {
        val tvExperienceSection = view.tv_experience_section
        val rvExperienceCompanies = view.findViewById<RecyclerView>(R.id.rv_content)
        var maxHeight = rvExperienceCompanies.measuredHeight
    }

    class EducationViewHolder(view: View) : SectionViewHolder(view) {
        val tvEducationSection = view.tv_education_section
        val rvEducationHeaders = view.findViewById<RecyclerView>(R.id.rv_content)
        var maxHeight = rvEducationHeaders.measuredHeight
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder = when(viewType) {
        VIEWTYPE_SKILLS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            val viewHolder = SkillsViewHolder(listItem)

            viewHolder.rvSkillsHeaders.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.rvSkillsHeaders.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_PROJECTS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects, parent, false)
            val viewHolder = ProjectsViewHolder(listItem)

            viewHolder.rvProjectsHeaders.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.rvProjectsHeaders.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_EXPERIENCE -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience, parent, false)
            val viewHolder = ExperienceViewHolder(listItem)

            viewHolder.rvExperienceCompanies.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.rvExperienceCompanies.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_EDUCATION -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_education, parent, false)
            val viewHolder = EducationViewHolder(listItem)

            viewHolder.rvEducationHeaders.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.rvEducationHeaders.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
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
                (holder as SkillsViewHolder).tvSkillsSection.text = skill.sectionName
                val skillsHeadersAdapter = SkillsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                holder.rvSkillsHeaders.apply {
                    setHasFixedSize(true)
                    layoutManager = linearLayoutManager
                    adapter = skillsHeadersAdapter
                }
                holder.itemView.setOnClickListener {
                    if ((it as CardView).isCollapsed()) {
                        it.expand(holder.maxHeight)
                    } else {
                        it.collapse()
                    }
                }

            }
            VIEWTYPE_PROJECTS -> {
                val project = sections.elementAt(position) as Projects
                (holder as ProjectsViewHolder).tvProjectsSection.text = project.sectionName
                val projectsHeadersAdapter = ProjectsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                holder.rvProjectsHeaders.apply {
                    layoutManager = linearLayoutManager
                    adapter = projectsHeadersAdapter
                }
                holder.itemView.setOnClickListener {
                    if ((it as CardView).isCollapsed()) {
                        it.expand(holder.maxHeight)
                    } else {
                        it.collapse()
                    }
                }

            }
            VIEWTYPE_EXPERIENCE -> {
                val experience = sections.elementAt(position) as Experience
                (holder as ExperienceViewHolder).tvExperienceSection.text = experience.sectionName
                val experienceCompaniesAdapter = ExperienceCompaniesAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                holder.rvExperienceCompanies.apply {
                    layoutManager = linearLayoutManager
                    adapter = experienceCompaniesAdapter
                }
                holder.itemView.setOnClickListener {
                    if ((it as CardView).isCollapsed()) {
                        it.expand(holder.maxHeight)
                    } else {
                        it.collapse()
                    }
                }
            }
            VIEWTYPE_EDUCATION -> {
                val education = sections.elementAt(position) as Education
                (holder as EducationViewHolder).tvEducationSection.text = education.sectionName
                val educationAdapter = EducationHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                holder.rvEducationHeaders.apply {
                    layoutManager = linearLayoutManager
                    adapter = educationAdapter
                }
                holder.itemView.setOnClickListener {
                    if ((it as CardView).isCollapsed()) {
                        it.expand(holder.maxHeight)
                    } else {
                        it.collapse()
                    }
                }
            }
            else -> {
                holder.tvSection.text = sections.elementAt(position).sectionName
            }
        }

    }

    override fun getItemCount() = sections.size


    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> VIEWTYPE_SKILLS
        1 -> VIEWTYPE_PROJECTS
        2 -> VIEWTYPE_EXPERIENCE
        3 -> VIEWTYPE_EDUCATION
        else -> VIEWTYPE_GENERIC
    }

}

class SkillsHeaderAdapter(val context: Context) : RecyclerView.Adapter<SkillsHeaderAdapter.SkillsHeaderViewHolder>() {

    private val headers = Skills.headers

    class SkillsHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSkillsHeader = view.tv_skills_header
        val rvBullets = view.rv_skills_bullets
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_headers, parent, false)
        return SkillsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: SkillsHeaderViewHolder, position: Int) {
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

open class ProjectsHeaderAdapter(val context: Context) : RecyclerView.Adapter<ProjectsHeaderAdapter.ProjectsHeaderViewHolder>() {

    val headers = Projects.headers
    val dates = Projects.dates
    val urls = Projects.urls

    class ProjectsHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProjectsHeader = view.tv_projects_header
        val tvProjectsDate = view.tv_projects_date
        val ivGithubLogo = view.findViewById<ImageView>(R.id.iv_github_logo_project)
        val rvBullets = view.rv_projects_bullets
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects_headers, parent, false)
        return ProjectsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: ProjectsHeaderViewHolder, position: Int) {
        holder.tvProjectsHeader.text = headers.elementAt(position)
        holder.tvProjectsDate.text = dates.elementAt(position)
        if (urls.elementAt(position).isEmpty()) {
            holder.ivGithubLogo.visibility = View.GONE
        } else {
            holder.ivGithubLogo.setBrowserClickListener(urls.elementAt(position), context)
        }

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

class ExperienceCompaniesAdapter(val context: Context) : RecyclerView.Adapter<ExperienceCompaniesAdapter.ExperienceCompaniesViewHolder>() {

    private val companies = Experience.companies
    private val positions = Experience.positions
    private val locations = Experience.locations
    private val dates = Experience.dates

    class ExperienceCompaniesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvExperienceCompany = view.tv_experience_company
        val tvExperiencePosition = view.tv_experience_position
        val tvExperienceLocation = view.tv_experience_location
        val tvExperienceDate = view.tv_experience_date
        val rvBullets = view.rv_experience_bullets
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceCompaniesViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience_companies, parent, false)
        return ExperienceCompaniesViewHolder(listItem)
    }

    override fun getItemCount() = companies.size

    override fun onBindViewHolder(holder: ExperienceCompaniesViewHolder, position: Int) {
        holder.tvExperienceCompany.text = companies.elementAt(position)
        holder.tvExperiencePosition.text = positions.elementAt(position)
        holder.tvExperienceLocation.text = locations.elementAt(position)
        holder.tvExperienceDate.text = dates.elementAt(position)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val experienceBulletAdapter = ExperienceBulletAdapter(Experience.bullets.elementAt(position))
        holder.rvBullets.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = experienceBulletAdapter
        }
    }
}

class ExperienceBulletAdapter(private val bullets: Set<String>) : RecyclerView.Adapter<ExperienceBulletAdapter.ExperienceBulletViewHolder>() {

    class ExperienceBulletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBullet = view.tv_experience_bullet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience_bullet, parent, false)
        return ExperienceBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: ExperienceBulletViewHolder, position: Int) {
        holder.tvBullet.text = "\u2022 ${bullets.elementAt(position)}"
    }
}

class EducationHeaderAdapter(val context: Context) : RecyclerView.Adapter<EducationHeaderAdapter.EducationHeaderViewHolder>() {

    private val schools = Education.schools
    private val degrees = Education.degrees
    private val dates = Education.dates

    class EducationHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivEducationSchool = view.iv_education_school_logo
        val tvEducationDegree = view.tv_education_degree
        val tvEducationDate = view.tv_education_date
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_education_headers, parent, false)
        return EducationHeaderViewHolder(listItem)
    }

    override fun getItemCount() = degrees.size

    override fun onBindViewHolder(holder: EducationHeaderViewHolder, position: Int) {
        holder.tvEducationDegree.text = degrees.elementAt(position)
        holder.tvEducationDate.text = dates.elementAt(position)

        val school = schools.elementAt(position)

        when(school) {
            "Udacity" -> {
                holder.ivEducationSchool.setImageResource(R.drawable.udacity)
                holder.ivEducationSchool.setBrowserClickListener("https://www.udacity.com/", context)
            }
            "Illinois Institute of Technology" -> {
                holder.ivEducationSchool.setImageResource(R.drawable.iit)
                holder.ivEducationSchool.setBrowserClickListener("https://web.iit.edu//", context)
            }
        }
    }
}