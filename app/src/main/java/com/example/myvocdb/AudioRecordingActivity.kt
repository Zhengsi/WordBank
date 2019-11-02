package com.example.myvocdb

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import kotlinx.android.synthetic.main.activity_audio_recording.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AudioRecordingActivity : AppCompatActivity() {

    private var timer: Chronometer? = null
    private var pathName: String? = null
    private var newText: String? = null
    private var newMean: String? = null
    private var fileName: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_recording)

        Toast.makeText(applicationContext, "You may record an audio note now.", Toast.LENGTH_LONG).show()

        timer = findViewById(R.id.timer)

        val intent = intent

        newText = intent.getStringExtra("NEW_WORD")
        newMean = intent.getStringExtra("NEW_MEAN")

        findViewById<TextView>(R.id.wordText).text = newText

        fileName = (SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date()) + ".mp3")

        //Toast.makeText(applicationContext, fileName, Toast.LENGTH_LONG).show()

        pathName = "/sdcard/WordBankRecordings" + File.separator + fileName

       // Toast.makeText(applicationContext, pathName, Toast.LENGTH_LONG).show()

        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(pathName)


        fab_start_recording.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                startRecording()
            }
        }

        fab_stop_recording.setOnClickListener {
            stopRecording()
        }

    }

    private fun startRecording() {

        try {
            //Toast.makeText(this, "Ready to start!", Toast.LENGTH_SHORT).show()
            mediaRecorder?.prepare()

            //Toast.makeText(this, "Prepared!", Toast.LENGTH_SHORT).show()
            mediaRecorder?.start()
            state = true

            timer?.base = SystemClock.elapsedRealtime()
            timer?.start()

            //Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

  /*  @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun pauseRecording() {
        if(state) {

            if(!recordingStopped){
                Toast.makeText(this,"Paused!", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                recordingStopped = true

                timer?.stop()

                pauseOffset = SystemClock.elapsedRealtime() - timer.

                fab_pause_recording.text = "Resume"
            }else{
                resumeRecording()
            }
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resumeRecording() {
        Toast.makeText(this,"Resumed!", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
        fab_pause_recording.text = "Pause"
        recordingStopped = false
        timer?.start()
    }*/

    private fun stopRecording(){
        if(state){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            Toast.makeText(this, "The recording is stopped.", Toast.LENGTH_SHORT).show()
            state = false
            timer?.base = SystemClock.elapsedRealtime()
            timer?.stop()
        }else{
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }

    fun GoToRecordings (view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        /*intent = Intent()
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("file:///sdcard/WordBankRecordings/mp3")

        startActivityForResult(intent, 2)*/

        val dbHelper = DBHelper(this)

        //Toast.makeText(applicationContext, "Data not saved", Toast.LENGTH_LONG).show()
        val item = Item(newText.toString(), newMean.toString(), pathName.toString())

        Toast.makeText(applicationContext, "New entry has been saved.", Toast.LENGTH_LONG).show()
        dbHelper.addWord(item)


        val wpIntent = Intent(this, WordPageActivity::class.java)
        wpIntent.putExtra("FILE_PATH", pathName)
        //Toast.makeText(applicationContext, pathName, Toast.LENGTH_LONG).show()
        wpIntent.putExtra("NEW_WORD", newText)
        wpIntent.putExtra("NEW_MEAN", newMean)

        startActivity(wpIntent)

    }

}
