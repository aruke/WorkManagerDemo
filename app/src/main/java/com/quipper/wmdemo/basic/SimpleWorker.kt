package com.quipper.wmdemo.basic

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val delayInSeconds: Int = workerParams.inputData.getInt(KEY_DELAY, 0)

    override fun doWork(): Result {
        // Do intensive task here
        // For now, our old friend Thread.sleep can replace intensive task here.
        Thread.sleep(delayInSeconds * 1000L)

        // Return with success
        return Result.success()
    }

    companion object {
        const val KEY_DELAY = "com.quipper.wmdemo.DELAY"
    }

}