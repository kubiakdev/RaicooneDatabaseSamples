package com.kubiakpatryk.raicoonedatabasesamples

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kubiakpatryk.raicoonedatabasesamples.SQLite.DatabaseHandler
import com.kubiakpatryk.raicoonedatabasesamples.objectBox.ObjectBoxHandler
import com.kubiakpatryk.raicoonedatabasesamples.tempFiles.TempFilesHandler
import java.util.*

class MainActivity : AppCompatActivity(), Operations {

    private val databaseHandler = DatabaseHandler(this)
    private val boxStore = ObjectBoxHandler().box
    private val tempHandler = TempFilesHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doObjectBoxOperations()
        doSQLiteOperations()
        doTempFilesOperations()
    }

    override fun doSQLiteOperations() {
        databaseHandler.addEntity(getGeneratedEntityChangedToContentValues()) //add
        databaseHandler.updateEntity(0,
                getGeneratedEntityChangedToContentValues()) //update
        databaseHandler.getEntity(0) //get
        databaseHandler.removeEntity(0) //remove
    }

    override fun doObjectBoxOperations() {
        boxStore?.put(getGeneratedEntity()) //add
        val entity = boxStore?.get(getLastBoxEntityId()) //get
        /*change entity data*/
        if (entity != null) boxStore?.put(entity) // update
        boxStore?.remove(getLastBoxEntityId()) //remove
    }

    override fun doTempFilesOperations() {
        val path = "data/data/com.kubiakpatryk.raicoonedatabasesample/entity.json"
        tempHandler.addEntity(path, getGeneratedEntity()) //add
        tempHandler.updateEntity("new name", path) //update
        tempHandler.getEntity(path) //get
        tempHandler.removeEntity(path) //remove
    }

    private fun getGeneratedEntityChangedToContentValues(): ContentValues {
        val cachedEntity = getGeneratedEntity()
        val contentValues = ContentValues()
        contentValues.put("id", cachedEntity.id)
        contentValues.put("name", cachedEntity.name)
        contentValues.put("type", cachedEntity.type)
        contentValues.put("extension", cachedEntity.extension)
        contentValues.put("parent", cachedEntity.parent)
        contentValues.put("disc", cachedEntity.disc)
        contentValues.put("url", cachedEntity.url)
        contentValues.put("createdDate", cachedEntity.createdDate)
        contentValues.put("modifiedDate", cachedEntity.modifiedDate)
        return contentValues
    }

    private fun getGeneratedEntity(): FileEntity = FileEntity(
            0,
            "new Random Entity",
            "new Random type",
            "new Random extension",
            "new Random Parent",
            "new Random Disc",
            "new Random URL",
            Calendar.getInstance().time.toString(),
            Calendar.getInstance().time.toString())

    private fun getLastBoxEntityId() =
            App.boxStore!!.boxFor<FileEntity>(FileEntity::class.java)!!.all!!.size.toLong()
}
