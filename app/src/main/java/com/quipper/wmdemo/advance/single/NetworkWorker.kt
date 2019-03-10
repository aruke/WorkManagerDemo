package com.quipper.wmdemo.advance.single

import android.content.Context
import android.util.Log
import androidx.work.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkWorker(private val context: Context, private val params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        // Get the input data
        val userData = params.inputData.getStringArray(KEY_USER_DATA)
        val secretKey = params.inputData.getString(KEY_SECRET_KEY)

        secretKey ?: run {
            Log.e(TAG, "doWork: SecretKey not found in WorkParams")
            return Result.failure()
        }

        MockNetworkCall.initializeWithParameters(context, secretKey)

        return try {
            val result = MockNetworkCall.doLongRunningNetworkCall(userData)

            Result.success(result.toOutputData())

        } catch (ioError: IOException) {
            // This means there was a problem with network.
            Result.retry()
        } catch (otherError: Exception) {
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "NetworkWorker"
        private const val KEY_USER_DATA = "key_user_data"
        private const val KEY_SECRET_KEY = "key_secret_key"

        const val NAME = "com.quipper.wmdemo.NetworkWorker"

        fun buildRequest(secretKey: String, userData: Array<String>): OneTimeWorkRequest {

            val inputData = Data.Builder()
                    .putString(KEY_SECRET_KEY, secretKey)
                    .putStringArray(KEY_USER_DATA, userData)
                    .build()

            val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresCharging(true)
                    .build()

            return OneTimeWorkRequest.Builder(NetworkWorker::class.java)
                    .setInputData(inputData)
                    .setConstraints(constraints)
                    .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 15, TimeUnit.MINUTES)
                    .build()
        }
    }
}