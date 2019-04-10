package com.mjkrajsman.alu5.service
import android.app.IntentService
import android.content.Intent
import com.mjkrajsman.alu5.util.NotificationFactory


class Notification2Service: IntentService("Notification2Service") {


    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }

    override fun onHandleIntent(intent: Intent?) {
        notificationFactory.show(this, "Familiada o 16:35 na TVP2",
            "Nie przegap najlepszego programu rozrywkowego")
    }

}