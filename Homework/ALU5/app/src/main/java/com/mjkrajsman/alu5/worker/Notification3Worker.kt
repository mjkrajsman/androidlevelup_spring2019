package com.mjkrajsman.alu5.worker

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mjkrajsman.alu5.util.NotificationFactory


class Notification3Worker(private val context: Context, params: WorkerParameters) : Worker(context, params) {

    private val notificationFactory: NotificationFactory by lazy { NotificationFactory() }


    override fun doWork(): Result {
        Handler(Looper.getMainLooper()).post {
            notificationFactory.show(context, "Obserwujemy Cię!", "Ładowarka podłączona :)")
        }
        return Result.success()
    }
}