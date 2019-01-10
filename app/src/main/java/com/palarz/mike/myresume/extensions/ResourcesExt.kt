package com.palarz.mike.myresume.extensions

import android.content.Context
import android.content.res.AssetManager
import android.os.Environment
import android.util.Log
import com.palarz.mike.myresume.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception
import java.lang.RuntimeException

fun AssetManager.getFile(fileName : String, context: Context) : File {

    val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    if (!root.exists()) {
        val result = root.mkdirs()
        Log.d("AssetManagerExt", "Result of mkdirs(): $result")
    }

    val file = File(root, fileName)

    if (!file.exists()) try {
        val inputStream = context.assets.open(fileName)
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