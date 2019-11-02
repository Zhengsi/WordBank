package com.example.myvocdb

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_audio_recording.*
import kotlinx.android.synthetic.main.activity_word_page.*
import java.io.File

class WordPageActivity : AppCompatActivity() {

    var audioFileName: String?= null

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_page)

        val intent = intent

        val newText = intent.getStringExtra("NEW_WORD")
        val newMean = intent.getStringExtra("NEW_MEAN")

        findViewById<TextView>(R.id.textView_word).text = newText
        findViewById<TextView>(R.id.textView_mean).text = newMean


        audioFileName = intent.getStringExtra("FILE_PATH")

        //Toast.makeText(this, audioFileName, Toast.LENGTH_SHORT).show()

        mediaPlayer = MediaPlayer.create(this, Uri.parse(audioFileName))

        btn_start_playing.setOnClickListener {

            StartPlaying()
        }

       /* btn_stop_playing.setOnClickListener {

            StopPlaying()
        }*/
    }

    fun StartPlaying(){
        //Toast.makeText(this, audioFileName.toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, "ready", Toast.LENGTH_SHORT).show()
        mediaPlayer?.start()
    }


    /*fun StopPlaying(){

        mediaPlayer?.stop()

    }*/

    fun GoToWordList (view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        val wlIntent = Intent(this, WordListActivity::class.java)
        startActivity(wlIntent)


    }


}
