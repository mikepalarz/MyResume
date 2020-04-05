package com.palarz.mike.myresume.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SectionTest {

    @Test
    fun `given a new Skills instance, when the section name is accessed, then its value is correct`() {
        val section = Skills()
        assertEquals("Section name is not correct", "Skills", section.sectionName)
    }

    @Test
    fun `given a new Projects instance, when the section name is accessed, then its value is correct`() {
        val section = Projects()
        assertEquals("Section name is not correct", "Projects", section.sectionName)
    }

    @Test
    fun `given a new Experience instance, when the section name is accessed, then its value is correct`() {
        val section = Experience()
        assertEquals("Section name is not correct", "Experience", section.sectionName)
    }

    @Test
    fun `given a new Education instance, when the section name is accessed, then its value is correct`() {
        val section = Education()
        assertEquals("Section name is not correct", "Education", section.sectionName)
    }

}

@RunWith(JUnit4::class)
class SkillsTest {

    @Test
    fun `given the Skills headers, when they are accessed, then their values are correct`() {
        val headers = setOf("Programming Languages", "Third-Party Libraries", "Packages/Frameworks", "Miscellaneous")
        assertEquals("Headers are incorrect", headers, Skills.headers)
    }

    @Test
    fun `given the Skills bullets, when they are accessed, then their values are correct`() {
        val bullets = setOf(
            setOf("Java", "Kotlin", "VBA", "Python", "Google Apps Script", "C++", "Ruby"),
            setOf("Picasso", "Butterknife", "RxJava", "Retrofit", "ExoPlayer", "Timber"),
            setOf("Firebase", "SQLite", "Espresso", "Gradle", "Maven"),
            setOf("git", "GitHub", "RegEx", "Bash", "Agile development", "JIRA")
        )
        assertEquals("Bullets are incorrect", bullets, Skills.bullets)
    }

}

@RunWith(JUnit4::class)
class ProjectsTest {

    @Test
    fun `given the Projects headers, when they are accessed, then their values are correct`() {
        val headers = setOf("Voyager Android", "JammyJamz Android App", "The Joker Android App", "Baking Recipes Android App", "Voice Traffic Automator", "Voice Traffic Data Processing")
        assertEquals("Headers are incorrect", headers, Projects.headers)
    }

    @Test
    fun `given the Projects dates, when they are accessed, then their values are correct`() {
        val dates = setOf("Present", "October 2018", "January 2018", "November 2017", "June 2018", "August 2017")
        assertEquals("Dates are incorrect", dates, Projects.dates)
    }

    @Test
    fun `given the Projects bullets, when they are accessed, then their values are correct`() {
        val bullets = setOf(
            setOf("Created a custom, enterprise-grade authenticator which adheres to OAuth 2.0 for Motorola employee login",
                "Configured network security so that CA files are included directly within codebase and VPN access is granted",
                "Partake in an agile development process by working through 2-week sprints and responsibly managing JIRA tasks"),
            setOf("Created a social network for music enthusiasts centered around sharing musical interests",
                "Utilized FirebaseUI for user authentication and Realtime Database for storing user-generated content",
                "Provided a search interface which involved using the Spotify Web API and properly handling the OAuth client credentials flow"),
            setOf("Used Gradle to create both free and paid flavors to modularize the construction of each variant of the app.",
                "Factored some of the functionality of the app into a Java library",
                "Adjusted the free flavor of the app to include both banner and interstitial ads using Google AdMob"),
            setOf("Verified user interfaces through intensive UI testing via Espresso",
                "Incorporated media loading by using ExoPlayer to show the user a video for each step of the recipe",
                "Offered responsive layouts for both tablets and handheld devices through the use of Java, fragments, and XML",
                "Provided a complete UX by creating a grocery list widget to show the user all of the necessary ingredients for a recipe"),
            setOf("Designed a Google Apps Script-based traffic setup tool which allowed the user to import all voice traffic parameters using a single Google Sheet",
                "Reduced the setup time of voice traffic simulations from several days to 15 minutes"),
            setOf("Wrote a VBA macro that performed data processing in order to determine the number of RF channels needed per site for a project estimated to be worth ~\$400 million",
                "Provided data-driven statistics from the traffic data which guided the team towards a more competitive approach to the capacity study")
        )
        assertEquals("Bullets are incorrect", bullets, Projects.bullets)
    }

    @Test
    fun `given the Projects URLs, when they are accessed, then their values are correct`() {
        val urls = listOf("",
            "https://github.com/mikepalarz/JammyJamz",
            "https://github.com/mikepalarz/TheJoker",
            "https://github.com/mikepalarz/BakingApp",
            "",
            "")
        assertEquals("URLs are incorrect", urls, Projects.urls)
    }

}

@RunWith(JUnit4::class)
class ExperienceTest {

    @Test
    fun `given the Experience companies, when they are accessed, then their values are correct`() {
        val companies = setOf("Motorola Solutions Inc.")
        assertEquals("Companies are incorrect", companies, Experience.companies)
    }

    @Test
    fun `given the Experience positions, when they are accessed, then their values are correct`() {
        val positions = setOf("Senior Systems Engineer")
        assertEquals("Positions are incorrect", positions, Experience.positions)
    }

    @Test
    fun `given the Experience locations, when they are accessed, then their values are correct`() {
        val locations = setOf("Chicago, IL")
        assertEquals("Locations are incorrect", locations, Experience.locations)
    }

    @Test
    fun `given the Experience dates, when they are accessed, then their values are correct`() {
        val dates = setOf("January 2012 - Present")
        assertEquals("Dates are incorrect", dates, Experience.dates)
    }

    @Test
    fun `given the Experience bullets, when they are accessed, then their values are correct`() {
        val bullets = setOf(
            setOf("Perform coverage simulations for public safety and utilities customers for client-facing projects that have a net worth of \$10-\$400 million",
                "Simulate OTA voice and data traffic to determine if a system has enough capacity without impacting mission-critical voice communications",
                "Partake in creative discussions with developers about impactful improvements to UX for proprietary software",
                "Represent the company by presenting results to both local and international customers (namely Morocco)",
                "Utilize a well-rounded set of communication skillsSectionName by mentoring less-experienced engineers, preparing documents and presentation slides for engineering best practices, guiding training sessions, and collaboratively working with other departments."
            )
        )
        assertEquals("Bullets are incorrect", bullets, Experience.bullets)
    }

}

@RunWith(JUnit4::class)
class EducationTest {

    @Test
    fun `given the Education schools, when they are accessed, then their values are correct`() {
        val schools = listOf("Udacity", "Illinois Institute of Technology", "Illinois Institute of Technology")
        assertEquals("Companies are incorrect", schools, Education.schools)
    }

    @Test
    fun `given the Education degrees, when they are accessed, then their values are correct`() {
        val degrees = setOf("Android Development Nanodegree", "M. Sc. in Electrical Engineering", "B. Sc. in Electrical Engineering")
        assertEquals("Positions are incorrect", degrees, Education.degrees)
    }

    @Test
    fun `given the Education dates, when they are accessed, then their values are correct`() {
        val dates = setOf("October 2018", "May 2015", "December 2012")
        assertEquals("Locations are incorrect", dates, Education.dates)
    }

}