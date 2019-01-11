package com.palarz.mike.myresume.extensions

import android.content.Context
import android.content.res.Resources
import com.palarz.mike.myresume.R
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.lang.RuntimeException

fun Resources.getResumeFile(context: Context) : File {
    val file = File(context.filesDir, context.getString(R.string.resume_file_name))

    if (!file.exists()) try {
        val inputStream = this.openRawResource(R.raw.resume_january2019)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val outputStream = FileOutputStream(file)
        outputStream.write(buffer)
        outputStream.close()
    } catch (e : Exception) {
        throw RuntimeException(e)
    }

    return file
}