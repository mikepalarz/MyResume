package com.palarz.mike.myresume.ui.model

open class Section(val section: String)

class Skills(section: String) : Section(section) {

    companion object {
        val headers = setOf("Programming Languages", "Third-Party Libraries", "Packages/Frameworks", "Miscellaneous")
        val bullets = setOf(
                        setOf("Java", "VBA", "Python", "Google Apps Script", "C++", "Ruby"),
                        setOf("Picasso", "Butterknife", "Retrofit", "ExoPlayer", "Timber"),
                        setOf("Firebase", "SQLite", "Espresso", "Gradle", "Maven"),
                        setOf("git", "GitHub", "RegEx", "Bash")
        )
    }
}