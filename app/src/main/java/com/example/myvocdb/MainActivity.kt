package com.example.myvocdb

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    lateinit var alarmManager: AlarmManager
    var alarmPendingIntent: PendingIntent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The alarmIntent will lead us to the Receiver
        val alarmIntent = Intent(this, Receiver::class.java)

        alarmPendingIntent =
            PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // Create an alarm

        //Toast.makeText(this, "alarm ready", Toast.LENGTH_SHORT).show()

        val Duration_Switch = findViewById<Switch>(R.id.Duration_Switch)
        val Freq_Switch = findViewById<Switch>(R.id.FreqSwitch)

        Duration_Switch?.setOnCheckedChangeListener{ _, isChecked ->

            if(isChecked){

                Freq_Switch?.setOnCheckedChangeListener { _, isChecked ->

                    if (isChecked) {

                       /* alarmManager.set(
                            AlarmManager.RTC,
                            SystemClock.elapsedRealtime(),
                            alarmPendingIntent*/


                        alarmManager.setInexactRepeating(
                            AlarmManager.RTC,
                            SystemClock.elapsedRealtime(),
                            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                            alarmPendingIntent
                        )
                        Toast.makeText(this, "15 min", Toast.LENGTH_SHORT).show()

                    } else {

                        alarmManager.setInexactRepeating(
                            AlarmManager.RTC,
                            SystemClock.elapsedRealtime(),
                            AlarmManager.INTERVAL_HOUR,
                            alarmPendingIntent
                        )
                        Toast.makeText(this, "1 hour", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                alarmManager.cancel(alarmPendingIntent)
                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //------------------------------------------------------------------------------

    fun newEntry(view: View){

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

        //val wlIntent = Intent(this, WordListActivity::class.java)
        val wlIntent = Intent(this, WordListActivity::class.java)
        startActivity(wlIntent)

    }

}