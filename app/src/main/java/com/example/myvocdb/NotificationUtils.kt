package com.example.myvocdb

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myvocdb.DBHelper.DBHelper


// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


lateinit var notificationManager: NotificationManager
lateinit var notificationChannel: NotificationChannel
lateinit var builder: Notification.Builder

val channelId = "com.example.myvocdb"
val description = "My notification"



fun NotificationManager.sendNotification(applicationContext: Context) {

    var dbHelper = DBHelper(applicationContext)

    var data = dbHelper.readAllWords()

    val randomInteger = (1..data.size).shuffled().first()

    var i = randomInteger

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
            .setContentTitle(data[i].word)
            .setContentText(data[i].meaning)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_VOCDB_REVIEW)
            .setGroupSummary(true)
    }
    else{
        builder = Notification.Builder(applicationContext)
            .setContentTitle(data[i].word)
            .setContentText(data[i].meaning)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_VOCDB_REVIEW)
            .setGroupSummary(true)

    }
    val id = System.currentTimeMillis().toInt()
    notificationManager.notify(id, builder.build())

}



// TODO: Step 1.14 Cancel all notifications
/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
