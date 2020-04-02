package com.palarz.mike.myresume.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SectionTest {

    @Test
    fun `given a new section instance, when the property is read, then its value is correct`() {
        val section = Section("My Section")
        assertEquals("Properties should be equal", "My Section", section.section)
    }

}