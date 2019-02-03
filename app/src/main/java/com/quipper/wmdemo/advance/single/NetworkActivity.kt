package com.quipper.wmdemo.advance.single

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.quipper.wmdemo.R

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val secretKey: String = AppPreferences.getSecretKey()
        val userData: Array<String> = UserPreferences.getUserData()


        // Use startUniqueWork

        val workManager = WorkManager.getInstance()

        workManager.beginUniqueWork(
            NetworkWorker.NAME,
            ExistingWorkPolicy.KEEP,
            NetworkWorker.buildRequest(secretKey, userData)
        ).enqueue()

    }
}