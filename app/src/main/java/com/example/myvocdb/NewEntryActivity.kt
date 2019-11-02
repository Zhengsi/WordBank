package com.example.myvocdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_new_entry.*
import org.w3c.dom.Text

class NewEntryActivity : AppCompatActivity() {

    //lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_entry)

    }

    fun goToAudioNote(view: View) {

        //val dbHelper = DBHelper(this)
        var text_word = findViewById<TextInputEditText>(R.id.edit_text_new_word)
        var text_meaning = findViewById<TextInputEditText>(R.id.edit_text_meaning)

       // val item = Item(text_word.text.toString(), text_meaning.text.toString(), "pending")
        //dbHelper.addWord(item)
        //clear all edittext
        val audioIntent = Intent(this, AudioRecordingActivity::class.java)
        //Toast.makeText(applicationContext, text_word.text.toString(), Toast.LENGTH_LONG).show()
        audioIntent.putExtra ("NEW_WORD", text_word.text.toString())
        audioIntent.putExtra("NEW_MEAN", text_meaning.text.toString())
        this.edit_text_new_word.setText("")
        this.edit_text_meaning.setText("")
        //Toast.makeText(applicationContext, "saved to db", Toast.LENGTH_LONG).show()

        startActivity(audioIntent)

    }

   /* fun addUser(view: View) {
        val dbHelper = DBHelper(this)
        val text_word = findViewById<TextInputEditText>(R.id.edit_text_new_word)
        val text_meaning = findViewById<TextInputEditText>(R.id.edit_text_meaning)

        val item = Item(text_word.text.toString(), text_meaning.text.toString())
        dbHelper.addWord(item)
        //clear all edittext
        this.edit_text_new_word.setText("")
        this.edit_text_meaning.setText("")
    }*/
}
