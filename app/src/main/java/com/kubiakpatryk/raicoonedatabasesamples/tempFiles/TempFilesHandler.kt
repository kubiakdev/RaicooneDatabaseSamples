package com.kubiakpatryk.raicoonedatabasesamples.tempFiles

import com.google.gson.Gson
import com.kubiakpatryk.raicoonedatabasesamples.FileEntity
import java.io.File

class TempFilesHandler {

    fun addEntity(path: String, entity: FileEntity) =
            File(path).writeText(Gson().toJson(entity))

    fun getEntity(path: String): FileEntity = Gson().fromJson(
            File(path).bufferedReader().use { it.readLine() },
           FileEntity::class.java)

    fun updateEntity(name: String, path: String) {
        val tempEntity = getEntity(path)
        tempEntity.name = name
        addEntity(path, tempEntity)
    }

    fun removeEntity(path: String) = File(path).delete()
}