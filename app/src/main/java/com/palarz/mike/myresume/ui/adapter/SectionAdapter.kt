package com.palarz.mike.myresume.ui.adapter

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import timber.log.Timber

private const val VIEWTYPE_SKILLS = 1
private const val VIEWTYPE_PROJECTS = 2
private const val VIEWTYPE_EXPERIENCE = 3
private const val VIEWTYPE_EDUCATION = 4

interface BindingViewHolder {
    fun bind(position: Int)
}

class SectionAdapter(val context: Context) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>(){

    private val sections = setOf(Skills, Projects, Experience, Education)

    abstract inner class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view), BindingViewHolder {
        override fun bind(position: Int) {
            itemView.setOnClickListener {
                if ((it as CardView).isCollapsed()) {
                    it.expand(maxHeight)
                } else {
                    it.collapse()
                }
            }
        }
        abstract val sectionName: TextView
        abstract val headers: RecyclerView
        abstract var maxHeight: Int
    }

    inner class SkillsViewHolder(view: View) : SectionViewHolder(view) {
        override val sectionName = view.tv_skills_section
        override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
        override var maxHeight = headers.measuredHeight

        override fun bind(position: Int) {
            super.bind(position)
            val skill = sections.elementAt(position) as Skills
            sectionName.text = skill.sectionName
            val skillsHeadersAdapter = SkillsHeaderAdapter(context)
            val linearLayoutManager =
                LinearLayoutManager(context)
            headers.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = skillsHeadersAdapter
            }

        }
    }

    inner class ProjectsViewHolder(view: View) : SectionViewHolder(view) {
        override val sectionName= view.tv_projects_section
        override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
        override var maxHeight = headers.measuredHeight

        override fun bind(position: Int) {
            super.bind(position)
            val project = sections.elementAt(position) as Projects
            sectionName.text = project.sectionName
            val projectsHeadersAdapter = ProjectsHeaderAdapter(context)
            val linearLayoutManager =
                LinearLayoutManager(context)
            headers.apply {
                layoutManager = linearLayoutManager
                adapter = projectsHeadersAdapter
            }
        }
    }

    inner class ExperienceViewHolder(view: View) : SectionViewHolder(view) {
        override val sectionName = view.tv_experience_section
        override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
        override var maxHeight = headers.measuredHeight

        override fun bind(position: Int) {
            super.bind(position)
            val experience = sections.elementAt(position) as Experience
            sectionName.text = experience.sectionName
            val experienceCompaniesAdapter = ExperienceCompaniesAdapter(context)
            val linearLayoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            headers.apply {
                layoutManager = linearLayoutManager
                adapter = experienceCompaniesAdapter
            }
        }
    }

    inner class EducationViewHolder(view: View) : SectionViewHolder(view) {
        override val sectionName = view.tv_education_section
        override val headers = view.findViewById<RecyclerView>(R.id.rv_content)
        override var maxHeight = headers.measuredHeight

        override fun bind(position: Int) {
            super.bind(position)
            val education = sections.elementAt(position) as Education
            sectionName.text = education.sectionName
            val educationAdapter = EducationHeaderAdapter(context)
            val linearLayoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            headers.apply {
                layoutManager = linearLayoutManager
                adapter = educationAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder = when(viewType) {
        VIEWTYPE_SKILLS -> {
            val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills, parent, false)
            val viewHolder = SkillsViewHolder(listItem)

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
            val viewHolder = ProjectsViewHolder(listItem)

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
            val viewHolder = ExperienceViewHolder(listItem)

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
            val viewHolder = EducationViewHolder(listItem)

            viewHolder.headers.viewTreeObserver.addOnDrawListener{
                val height = viewHolder.headers.measuredHeight
                if (height > viewHolder.maxHeight) {
                    viewHolder.maxHeight = height
                }
            }

            viewHolder
        }
        else -> {
            Timber.e("ViewHolder could not be created")
            throw RuntimeException("ViewHolder could not be created")
        }
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {

        when(holder.itemViewType) {
            VIEWTYPE_SKILLS -> {
                (holder as SkillsViewHolder).bind(position)

            }
            VIEWTYPE_PROJECTS -> {
                (holder as ProjectsViewHolder).bind(position)

            }
            VIEWTYPE_EXPERIENCE -> {
                (holder as ExperienceViewHolder).bind(position)
            }
            VIEWTYPE_EDUCATION -> {
                (holder as EducationViewHolder).bind(position)
            }
        }

    }

    override fun getItemCount() = sections.size

    override fun getItemViewType(position: Int): Int = when (sections.elementAt(position)) {
        is Skills -> VIEWTYPE_SKILLS
        is Projects -> VIEWTYPE_PROJECTS
        is Experience -> VIEWTYPE_EXPERIENCE
        is Education -> VIEWTYPE_EDUCATION
        else ->{
            Timber.e("View type not found")
            throw RuntimeException("View type not found")
        }
    }

}

abstract class SectionHeaderViewHolder(view: View): RecyclerView.ViewHolder(view), BindingViewHolder {
    abstract override fun bind(position: Int)
    abstract val header: TextView
    abstract val bullets: RecyclerView
}

abstract class SectionBulletViewHolder(view: View): RecyclerView.ViewHolder(view), BindingViewHolder {
    abstract override fun bind(position: Int)
    abstract val bullet: TextView
}

class SkillsHeaderAdapter(val context: Context) : RecyclerView.Adapter<SkillsHeaderAdapter.SkillsHeaderViewHolder>() {

    private val headers = Skills.headers

    inner class SkillsHeaderViewHolder(view: View) : SectionHeaderViewHolder(view) {
        override val header = view.tv_skills_header
        override val bullets = view.rv_skills_bullets

        override fun bind(position: Int) {
            header.text = headers.elementAt(position)
            val linearLayoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            val skillBulletAdapter = SkillsBulletAdapter(context, Skills.bullets.elementAt(position))
            bullets.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = skillBulletAdapter
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_headers, parent, false)
        return SkillsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: SkillsHeaderViewHolder, position: Int) {
        holder.bind(position)
    }
}

class SkillsBulletAdapter(private val context: Context, private val bullets: Set<String>) : RecyclerView.Adapter<SkillsBulletAdapter.SkillsBulletViewHolder>() {

    inner class SkillsBulletViewHolder(view: View) : SectionBulletViewHolder(view) {
        override val bullet = view.tv_skills_bullet

        override fun bind(position: Int) {
            bullet.text = context.getString(R.string.bullet, bullets.elementAt(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_skills_bullet, parent, false)
        return SkillsBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: SkillsBulletViewHolder, position: Int) {
        holder.bind(position)
    }
}

class ProjectsHeaderAdapter(val context: Context) : RecyclerView.Adapter<ProjectsHeaderAdapter.ProjectsHeaderViewHolder>() {

    private val headers = Projects.headers
    private val dates = Projects.dates
    private val urls = Projects.urls

    inner class ProjectsHeaderViewHolder(view: View): SectionHeaderViewHolder(view) {
        override val header = view.tv_projects_header
        private val tvProjectsDate = view.tv_projects_date
        private val ivGithubLogo = view.findViewById<ImageView>(R.id.iv_github_logo_project)
        override val bullets = view.rv_projects_bullets

        override fun bind(position: Int) {
            header.text = headers.elementAt(position)
            tvProjectsDate.text = dates.elementAt(position)
            if (urls.elementAt(position).isEmpty()) {
                ivGithubLogo.visibility = View.GONE
            } else {
                ivGithubLogo.setBrowserClickListener(urls.elementAt(position), context)
            }

            val linearLayoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            val projectBulletAdapter = ProjectsBulletAdapter(context, Projects.bullets.elementAt(position))
            bullets.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = projectBulletAdapter
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects_headers, parent, false)
        return ProjectsHeaderViewHolder(listItem)
    }

    override fun getItemCount() = headers.size

    override fun onBindViewHolder(holder: ProjectsHeaderViewHolder, position: Int) {
        holder.bind(position)
    }
}

class ProjectsBulletAdapter(private val context: Context, private val bullets: Set<String>) : RecyclerView.Adapter<ProjectsBulletAdapter.ProjectsBulletViewHolder>() {

    inner class ProjectsBulletViewHolder(view: View): SectionBulletViewHolder(view) {
        override val bullet = view.tv_projects_bullet

        override fun bind(position: Int) {
            bullet.text = context.getString(R.string.bullet, bullets.elementAt(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_projects_bullet, parent, false)
        return ProjectsBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: ProjectsBulletViewHolder, position: Int) {
        holder.bind(position)
    }
}

class ExperienceCompaniesAdapter(val context: Context) : RecyclerView.Adapter<ExperienceCompaniesAdapter.ExperienceCompaniesViewHolder>() {

    private val companies = Experience.headers
    private val positions = Experience.positions
    private val locations = Experience.locations
    private val dates = Experience.dates

    inner class ExperienceCompaniesViewHolder(view: View): SectionHeaderViewHolder(view) {
        override val header = view.tv_experience_company
        private val tvExperiencePosition = view.tv_experience_position
        private val tvExperienceLocation = view.tv_experience_location
        private val tvExperienceDate = view.tv_experience_date
        override val bullets = view.rv_experience_bullets

        override fun bind(position: Int) {
            header.text = companies.elementAt(position)
            tvExperiencePosition.text = positions.elementAt(position)
            tvExperienceLocation.text = locations.elementAt(position)
            tvExperienceDate.text = dates.elementAt(position)

            val linearLayoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            val experienceBulletAdapter = ExperienceBulletAdapter(context, Experience.bullets.elementAt(position))
            bullets.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = experienceBulletAdapter
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceCompaniesViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience_companies, parent, false)
        return ExperienceCompaniesViewHolder(listItem)
    }

    override fun getItemCount() = companies.size

    override fun onBindViewHolder(holder: ExperienceCompaniesViewHolder, position: Int) {
        holder.bind(position)
    }
}

class ExperienceBulletAdapter(private val context: Context, private val bullets: Set<String>) : RecyclerView.Adapter<ExperienceBulletAdapter.ExperienceBulletViewHolder>() {

    inner class ExperienceBulletViewHolder(view: View): SectionBulletViewHolder(view) {
        override val bullet = view.tv_experience_bullet

        override fun bind(position: Int) {
            bullet.text = context.getString(R.string.bullet, bullets.elementAt(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceBulletViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_experience_bullet, parent, false)
        return ExperienceBulletViewHolder(listItem)
    }

    override fun getItemCount() = bullets.size

    override fun onBindViewHolder(holder: ExperienceBulletViewHolder, position: Int) {
        holder.bind(position)
    }
}

class EducationHeaderAdapter(val context: Context) : RecyclerView.Adapter<EducationHeaderAdapter.EducationHeaderViewHolder>() {

    private val schools = Education.schools
    private val degrees = Education.headers
    private val dates = Education.bullets

    inner class EducationHeaderViewHolder(view: View): RecyclerView.ViewHolder(view), BindingViewHolder {
        private val ivEducationSchool = view.iv_education_school_logo
        private val tvEducationDegree = view.tv_education_degree
        private val tvEducationDate = view.tv_education_date

        override fun bind(position: Int) {
            tvEducationDegree.text = degrees.elementAt(position)
            tvEducationDate.text = dates.elementAt(0).elementAt(position)

            when(schools.elementAt(position)) {
                "Udacity" -> {
                    ivEducationSchool.setImageResource(R.drawable.udacity)
                    ivEducationSchool.setBrowserClickListener("https://www.udacity.com/", context)
                }
                "Illinois Institute of Technology" -> {
                    ivEducationSchool.setImageResource(R.drawable.iit)
                    ivEducationSchool.setBrowserClickListener("https://web.iit.edu/", context)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationHeaderViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_education_headers, parent, false)
        return EducationHeaderViewHolder(listItem)
    }

    override fun getItemCount() = degrees.size

    override fun onBindViewHolder(holder: EducationHeaderViewHolder, position: Int) {
        holder.bind(position)
    }
}