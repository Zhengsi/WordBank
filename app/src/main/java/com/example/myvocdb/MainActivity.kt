package com.example.myvocdb

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import com.example.myvocdb.DBHelper.DBHelper
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    lateinit var alarmManager: AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // The alarmIntent will lead us to the Receiver
        val alarmIntent = Intent(this, Receiver::class.java)

        val alarmPendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // Create an alarm

        Toast.makeText(this, "alarm ready", Toast.LENGTH_SHORT).show()
        alarmManager.setInexactRepeating(
                AlarmManager.RTC,
                SystemClock.elapsedRealtime(),
                AlarmManager.INTERVAL_HOUR * 2,
                alarmPendingIntent)
        Toast.makeText(this, "alarm send", Toast.LENGTH_SHORT).show()
        //-------------------------------------------------------------------------------------
       /* val randomInteger = (1..data.size).shuffled().first()


        var i = randomInteger

        val intent = Intent(applicationContext, WordListActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)


            builder = Notification.Builder(applicationContext, channelId)
                .setContentTitle(data[i].word.toString())
                .setContentText(data[i].meaning)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
        }
        else{
            builder = Notification.Builder(applicationContext)
                .setContentTitle(data[i].word)
                .setContentText(data[i].meaning)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)

        }
        notificationManager.notify(0, builder.build())*/

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