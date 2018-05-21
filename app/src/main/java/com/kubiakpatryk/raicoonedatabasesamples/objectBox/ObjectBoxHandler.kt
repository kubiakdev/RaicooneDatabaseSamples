package com.kubiakpatryk.raicoonedatabasesamples.objectBox

import com.kubiakpatryk.raicoonedatabasesamples.App
import com.kubiakpatryk.raicoonedatabasesamples.FileEntity
import io.objectbox.Box

class ObjectBoxHandler {
    var box : Box<FileEntity>? = null

    init {
      box = App.boxStore!!.boxFor<FileEntity>(FileEntity::class.java)
    }
}