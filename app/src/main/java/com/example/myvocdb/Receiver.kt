package com.example.myvocdb

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


class Receiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {

        //Log.d("Receiver", "this is receiver")
        Toast.makeText(context, "this is receiver", Toast.LENGTH_SHORT).show()


        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.sendNotification(
            context
        )
    }

}