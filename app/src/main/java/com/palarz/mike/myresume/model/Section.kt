package com.palarz.mike.myresume.model

abstract class Section {
    abstract val sectionName: String
    abstract val headers: Set<String>
    abstract val bullets: Set<Set<String>>
}

object Skills : Section() {
    override val sectionName: String = "Skills"
    override val headers = setOf("Programming Languages", "Third-Party Libraries", "Packages/Frameworks", "Miscellaneous")
    override val bullets = setOf(
        setOf("Java", "Kotlin", "VBA", "Python", "Google Apps Script", "C++", "Ruby"),
        setOf("Picasso", "Butterknife", "RxJava", "Retrofit", "ExoPlayer", "Timber"),
        setOf("Firebase", "SQLite", "Espresso", "Gradle", "Maven"),
        setOf("git", "GitHub", "RegEx", "Bash", "Agile development", "JIRA")
    )

}

object Projects : Section() {

    override val sectionName: String = "Projects"
    override val headers = setOf("Voyager Android", "JammyJamz Android App", "The Joker Android App", "Baking Recipes Android App", "Voice Traffic Automator", "Voice Traffic Data Processing")
    override val bullets = setOf(
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
    val dates = setOf("Present", "October 2018", "January 2018", "November 2017", "June 2018", "August 2017")
    val urls = listOf("",
        "https://github.com/mikepalarz/JammyJamz",
        "https://github.com/mikepalarz/TheJoker",
        "https://github.com/mikepalarz/BakingApp",
        "",
        "")

}

object Experience : Section() {
    override val sectionName = "Experience"
    override val headers = setOf("Motorola Solutions Inc.")
    override val bullets = setOf(
        setOf("Perform coverage simulations for public safety and utilities customers for client-facing projects that have a net worth of \$10-\$400 million",
            "Simulate OTA voice and data traffic to determine if a system has enough capacity without impacting mission-critical voice communications",
            "Partake in creative discussions with developers about impactful improvements to UX for proprietary software",
            "Represent the company by presenting results to both local and international customers (namely Morocco)",
            "Utilize a well-rounded set of communication skillsSectionName by mentoring less-experienced engineers, preparing documents and presentation slides for engineering best practices, guiding training sessions, and collaboratively working with other departments."
        )
    )
    val positions = setOf("Senior Systems Engineer")
    val locations = setOf("Chicago, IL")
    val dates = setOf("January 2012 - Present")

}

object Education: Section() {
    override val sectionName = "Education"
    override val headers = setOf("Android Development Nanodegree", "M. Sc. in Electrical Engineering", "B. Sc. in Electrical Engineering")
    override val bullets = setOf(setOf("October 2018", "May 2015", "December 2012"))
    val schools = listOf("Udacity", "Illinois Institute of Technology", "Illinois Institute of Technology")

}