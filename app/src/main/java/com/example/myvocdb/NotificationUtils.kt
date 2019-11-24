package com.example.myvocdb


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import java.util.*
import kotlin.random.Random

lateinit var notificationManager: NotificationManager
lateinit var notificationChannel: NotificationChannel
lateinit var builder: Notification.Builder

val channelId = "com.example.myvocdb"
val description = "My notification"


fun NotificationManager.sendNotification(applicationContext: Context) {


    var dbHelper = DBHelper(applicationContext)

    var data = dbHelper.readAllWords()

    //--------------------------------------------------------------------

    val randomInteger = Random.nextInt(0, data.size - 1)

    val i = randomInteger


    //---------------------------------------------------------------------
    val intent = Intent(applicationContext, WordListActivity::class.java)

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        0,
        intent,
        PendingIntent.FLAG_ONE_SHOT)

    val GROUP_VOCDB_REVIEW= "com.example.myvocdb.REVIEW"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)


        builder = Notification.Builder(applicationContext, channelId)
            .setContentTitle(data[data.size - i].word)
            .setContentText(data[data.size - i].meaning)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_VOCDB_REVIEW)
            .setGroupSummary(true)

    }
    else{
        builder = Notification.Builder(applicationContext)
            .setContentTitle(data[data.size - i].word)
            .setContentText(data[data.size - i].meaning)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_VOCDB_REVIEW)
            .setGroupSummary(true)

    }
    val id = System.currentTimeMillis().toInt()
    notificationManager.notify(id, builder.build())

}



fun NotificationManager.cancelNotifications() {
    cancelAll()
}
