package com.kubiakpatryk.raicoonedatabasesamples.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.kubiakpatryk.raicoonedatabasesamples.FileEntity

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "FilesDB.db"
        const val DB_VERSION = 1

        const val tableName = "main_table"
        const val id = "id"
        const val name = "name"
        const val type = "type"
        const val extension = "extension"
        const val parent = "parent"
        const val disc = "disc"
        const val url = "url"
        const val createdDate = "created_date"
        const val modifiedDate = "modified_date"
    }

    private var sqlObj: SQLiteDatabase? = this.writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE IF NOT EXISTS $tableName" +
                "($id INTEGER PRIMARY KEY," +
                " $name TEXT, " +
                " $type TEXT, " +
                " $extension TEXT, " +
                " $parent TEXT, " +
                " $disc TEXT, " +
                " $url TEXT, " +
                " $createdDate TEXT, " +
                " $modifiedDate TEXT, " + ");")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(p0)
    }

    fun getEntity(id: Long): FileEntity? {
        val columns = arrayOf(
                "id",
                "name",
                "type",
                "extension",
                "parent",
                "disc",
                "url",
                "createdDate",
                "modifiedDate")
        val cursor = SQLiteQueryBuilder().query(sqlObj, columns, "id=?",
                arrayOf(id.toString()), null, null, null)
        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val extension = cursor.getString(cursor.getColumnIndex("extension"))
            val parent = cursor.getString(cursor.getColumnIndex("parent"))
            val disc = cursor.getString(cursor.getColumnIndex("disc"))
            val url = cursor.getString(cursor.getColumnIndex("url"))
            val createdDate = cursor.getString(cursor.getColumnIndex("createdDate"))
            val modifiedDate = cursor.getString(cursor.getColumnIndex("modifiedDate"))

            return FileEntity(
                    id,
                    name,
                    type,
                    extension,
                    parent,
                    disc,
                    url,
                    createdDate,
                    modifiedDate)
        } else return null
    }

    fun addEntity(values: ContentValues) = sqlObj?.insert(tableName, "", values)

    fun updateEntity(id: Long, values: ContentValues) = sqlObj?.update(tableName, values,
            "id=?", arrayOf(id.toString()))

    fun removeEntity(id: Long) = sqlObj?.delete(tableName, "id=?", arrayOf(id.toString()))
}