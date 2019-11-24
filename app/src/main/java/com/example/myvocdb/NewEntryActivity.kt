package com.example.myvocdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_new_entry.*
import kotlinx.android.synthetic.main.row_item.*
import java.lang.Exception
import java.util.*

class NewEntryActivity : AppCompatActivity() {


    private var  REQUEST_CODE_SPEECH_INPUT_WORD = 101
    private var  REQUEST_CODE_SPEECH_INPUT_MEAN = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_entry)

        //this.textView_new_word.text = ""
        //this.textView_new_meaning.text = ""

        voice_word_Btn.setOnClickListener {
          speak(REQUEST_CODE_SPEECH_INPUT_WORD)
        }
        voice_mean_Btn.setOnClickListener {
            speak(REQUEST_CODE_SPEECH_INPUT_MEAN)
        }

    }

    fun goToAudioNote(view: View) {

        val dbHelper = DBHelper(this)

        var text_word = edit_text_new_word.text
        var text_meaning = edit_text_meaning.text


        //Toast.makeText(applicationContext, "Data not saved", Toast.LENGTH_LONG).show()
        val item = Item(text_word.toString(), text_meaning.toString(), "")

        Toast.makeText(applicationContext, "New entry has been saved.", Toast.LENGTH_LONG).show()
        dbHelper.addWord(item)


        val audioIntent = Intent(this, WordListActivity::class.java)
        //Toast.makeText(applicationContext, text_word.text.toString(), Toast.LENGTH_LONG).show()
        audioIntent.putExtra ("NEW_WORD", text_word.toString())
        audioIntent.putExtra("NEW_MEAN", text_meaning.toString())
        this.edit_text_new_word.setText("")
        this.edit_text_meaning.setText("")
        //this.textView_new_word.text = ""
        //this.textView_new_meaning.text = ""
        //Toast.makeText(applicationContext, "saved to db", Toast.LENGTH_LONG).show()

        startActivity(audioIntent)

    }

    /*fun addUser(view: View) {
        val dbHelper = DBHelper(this)
        val text_word = findViewById<TextInputEditText>(R.id.edit_text_new_word)
        val text_meaning = findViewById<TextInputEditText>(R.id.edit_text_meaning)

        val item = Item(text_word.text.toString(), text_meaning.text.toString())
        dbHelper.addWord(item)
        //clear all edittext
        this.edit_text_new_word.setText("")
        this.edit_text_meaning.setText("")
    }*/


     fun speak(req: Int){
        // intent to show SpeechToText dialog
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speaking...")

        try {
            // if there is no error show SpeechToText dialog

            startActivityForResult(mIntent, req)
        }catch (e: Exception){
            // if there is error get error message and show in toast
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT). show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT_WORD -> {
                if (resultCode == Activity.RESULT_OK && null != data){

                    // get text from result
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    // set the text to text
                    edit_text_new_word.setText(result[0].toString())
                    //this.textView_new_word.text = edit_text_new_word.getText().toString()

                }
            }
            REQUEST_CODE_SPEECH_INPUT_MEAN -> {
                if (resultCode == Activity.RESULT_OK && null != data){

                    // get text from result
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    // set the text to text
                    edit_text_meaning.setText(result[0].toString())
                    //this.textView_new_meaning.text = edit_text_meaning.getText().toString()
                }
            }

        }
    }





}
