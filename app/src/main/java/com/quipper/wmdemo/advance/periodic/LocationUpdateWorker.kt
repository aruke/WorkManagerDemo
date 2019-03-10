package com.quipper.wmdemo.advance.periodic

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * TODO
 */
class LocationUpdateWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}