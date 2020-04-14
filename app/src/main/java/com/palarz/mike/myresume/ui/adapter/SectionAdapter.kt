package com.palarz.mike.myresume.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.palarz.mike.myresume.R
import com.palarz.mike.myresume.extensions.collapse
import com.palarz.mike.myresume.extensions.expand
import com.palarz.mike.myresume.extensions.isCollapsed
import com.palarz.mike.myresume.extensions.setBrowserClickListener
import com.palarz.mike.myresume.model.Education
import com.palarz.mike.myresume.model.Experience
import com.palarz.mike.myresume.model.Projects
import com.palarz.mike.myresume.model.Skills
import kotlinx.android.synthetic.main.list_item_education.view.*
import kotlinx.android.synthetic.main.list_item_education_headers.view.*
import kotlinx.android.synthetic.main.list_item_experience.view.*
import kotlinx.android.synthetic.main.list_item_experience_bullet.view.*
import kotlinx.android.synthetic.main.list_item_experience_companies.view.*
import kotlinx.android.synthetic.main.list_item_projects.view.*
import kotlinx.android.synthetic.main.list_item_projects_bullet.view.*
import kotlinx.android.synthetic.main.list_item_projects_headers.view.*
import kotlinx.android.synthetic.main.list_item_skills.view.*
import kotlinx.android.synthetic.main.list_item_skills_bullet.view.*
import kotlinx.android.synthetic.main.list_item_skills_headers.view.*

private const val VIEWTYPE_SKILLS = 1
private const val VIEWTYPE_PROJECTS = 2
private const val VIEWTYPE_EXPERIENCE = 3
private const val VIEWTYPE_EDUCATION = 4

// TODO: I feel like this can be cleaned up using sealed classes
class SectionAdapter(val context: Context) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>(){

    private val sections = setOf(Skills, Projects, Experience, Education)

    sealed class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind()
        abstract val sectionName: TextView
        abstract val headers: RecyclerView
        abstract var maxHeight: Int

        // TODO: Need to refactor onBindViewHolder() logic into bind() methods
        class SkillsViewHolder(view: View) : SectionViewHolder(view) {
            override val sectionName = view.tv_skills_section
            override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
            override var maxHeight = headers.measuredHeight

            override fun bind() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class ProjectsViewHolder(view: View) : SectionViewHolder(view) {
            override val sectionName= view.tv_projects_section
            override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
            override var maxHeight = headers.measuredHeight

            override fun bind() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class ExperienceViewHolder(view: View) : SectionViewHolder(view) {
            override val sectionName = view.tv_experience_section
            override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
            override var maxHeight = headers.measuredHeight

            override fun bind() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class EducationViewHolder(view: View) : SectionViewHolder(view) {
            override val sectionName = view.tv_education_section
            override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
            override var maxHeight = headers.measuredHeight

            override fun bind() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder = when(viewType) {
        VIEWTYPE_SKILLS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            val viewHolder = SectionViewHolder.SkillsViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_PROJECTS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects, parent, false)
            val viewHolder = SectionViewHolder.ProjectsViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_EXPERIENCE -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience, parent, false)
            val viewHolder = SectionViewHolder.ExperienceViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }

        VIEWTYPE_EDUCATION -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_education, parent, false)
            val viewHolder = SectionViewHolder.EducationViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }
        else -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            val viewHolder = SectionViewHolder.SkillsViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {

        when(holder.itemViewType) {
            VIEWTYPE_SKILLS -> {
                val skill = sections.elementAt(position) as Skills
                (holder as SectionViewHolder.SkillsViewHolder).sectionName.text = skill.sectionName
                val skillsHeadersAdapter = SkillsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                holder.headers.apply {
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
                (holder as SectionViewHolder.ProjectsViewHolder).sectionName.text = project.sectionName
                val projectsHeadersAdapter = ProjectsHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context)
                holder.headers.apply {
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
                (holder as SectionViewHolder.ExperienceViewHolder).sectionName.text = experience.sectionName
                val experienceCompaniesAdapter = ExperienceCompaniesAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                holder.headers.apply {
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
                (holder as SectionViewHolder.EducationViewHolder).sectionName.text = education.sectionName
                val educationAdapter = EducationHeaderAdapter(context)
                val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                holder.headers.apply {
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
        }

    }

    override fun getItemCount() = sections.size

    // TODO: What if you return the layout ID here? Would having the layout ID assigned as the viewtype simplify
    // onCreateViewHolder()?
    override fun getItemViewType(position: Int): Int = when (sections.elementAt(position)) {
        is Skills -> VIEWTYPE_SKILLS
        is Projects -> VIEWTYPE_PROJECTS
        is Experience -> VIEWTYPE_EXPERIENCE
        is Education -> VIEWTYPE_EDUCATION
        else -> throw RuntimeException("Viewtype not found")
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

    private val companies = Experience.headers
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
    private val degrees = Education.headers
    private val dates = Education.bullets

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
        holder.tvEducationDate.text = dates.elementAt(0).elementAt(position)

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