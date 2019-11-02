package com.example.myvocdb.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.myvocdb.Model.Item

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object{

        val DATABASE_NAME = "WordBank.db"

        //Table
        private val TABLE_NAME = "ITEM"
        private val COL_WORD = "WORD"
        private val COL_MEANING = "MEANING"
        private val COL_AUDIOPATH = "AUDIO_PATH"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query = "CREATE TABLE $TABLE_NAME($COL_WORD TEXT PRIMARY KEY, $COL_MEANING TEXT, $COL_AUDIOPATH TEXT)"
        db?.execSQL(query)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }



    // insert data to SQLite database
    fun addWord(item: Item){

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_WORD, item.word)
        contentValues.put(COL_MEANING, item.meaning)
        contentValues.put(COL_AUDIOPATH, item.audioNotePath)
        db.insert(TABLE_NAME, null, contentValues)

    }

    //update data with a value

    fun updateWord(word: String, meaning: String, audioNotePath: String):
            Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_WORD, word)
        contentValues.put(COL_MEANING, meaning)
        contentValues.put(COL_AUDIOPATH, audioNotePath)
        db.update(TABLE_NAME, contentValues, "WORD = ?" , arrayOf(word))
        return true
    }

    //delete a row based on the word
    fun deleteWord(word: String): Int{

        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "WORD = ?", arrayOf(word))

    }


    // The below getter property will return a Cursor
    fun readAllWords(): ArrayList<Item> {
        val items: ArrayList<Item> = ArrayList()
        val db = writableDatabase
        var cursor: Cursor? = null
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        var word: String
        var meaning: String
        var audioNotePath: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                word = cursor.getString(cursor.getColumnIndex(COL_WORD))
                meaning = cursor.getString(cursor.getColumnIndex(COL_MEANING))
                audioNotePath = cursor.getString(cursor.getColumnIndex(COL_AUDIOPATH))

                items.add(Item(word, meaning, audioNotePath))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return items
    }

}