package com.example.myvocdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun newEntry(view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        val newEntryIntent = Intent(this, NewEntryActivity::class.java)
        startActivity(newEntryIntent)

    }

   /* fun GoToAudio(view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        val audioIntent = Intent(this, AudioRecordingActivity::class.java)
        startActivity(audioIntent)


    }*/

    fun GoToWordList (view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        val wlIntent = Intent(this, WordListActivity::class.java)
        startActivity(wlIntent)


    }

}