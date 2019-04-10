package com.mjkrajsman.alu5

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.work.*
import com.mjkrajsman.alu5.service.Notification2Service
import com.mjkrajsman.alu5.service.Notification4Service
import com.mjkrajsman.alu5.util.NotificationFactory
import com.mjkrajsman.alu5.util.ext.fromAndroid
import com.mjkrajsman.alu5.worker.Notification3Worker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val notificationFactory by lazy { NotificationFactory() }
//    private val notification3Scheduler by lazy { Notification3Scheduler() }
    private val alarmManager: AlarmManager
        get() = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNotificationChannels()
        startNotification4Service()
        scan_button.setOnClickListener(this::onScanButtonClicked)
        stop_scanning_button.setOnClickListener(this::onStopScanButtonClicked)
        scheduleNotification3()
        scheduleNotification2()
    }


    private fun initNotificationChannels() {
        fromAndroid(Build.VERSION_CODES.O) { notificationFactory.createNotificationChannels(this) }
    }

    private fun scheduleNotification2() {

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 16)
            set(Calendar.MINUTE, 20)
            set(Calendar.SECOND, 0)
        }

        Intent(this, Notification2Service::class.java)
            .apply { action = "com.mjkrajsman.alu5.NOTIFY" }
            .let { PendingIntent.getService(this, 0, it, 0) }
            .let {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it)
            }
    }

    private fun scheduleNotification3() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true).setRequiredNetworkType(NetworkType.CONNECTED).build()
        val request = OneTimeWorkRequest.Builder(Notification3Worker::class.java)
            .setConstraints(constraints).build()
        WorkManager.getInstance()
            .enqueueUniqueWork(Notification3Worker::javaClass.name, ExistingWorkPolicy.REPLACE, request)
    }

    private fun onScanButtonClicked(view: View){
        startNotification4Service()
    }

    private fun onStopScanButtonClicked(view: View){
        stopNotification4Service()
    }

    private fun startNotification4Service(){
        ContextCompat.startForegroundService(this, Intent(this, Notification4Service::class.java))
    }

    private fun stopNotification4Service(){
        stopService(Intent(this, Notification4Service::class.java))
    }




}
