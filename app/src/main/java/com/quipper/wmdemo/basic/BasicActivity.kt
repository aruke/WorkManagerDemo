package com.quipper.wmdemo.basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.quipper.wmdemo.R

class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Some UI Setup you don't need to worry about
        setContentView(R.layout.activity_basic)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val inputData: Data = Data.Builder().putInt(SimpleWorker.KEY_DELAY, 5).build()

        val simpleWorkRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInputData(inputData)
            .build()

        // Every WorkRequest object has a auto-generated unique ID. You should save it if you need to get information about the work.
        val workerId = simpleWorkRequest.id

        WorkManager.getInstance().enqueue(simpleWorkRequest)

        WorkManager.getInstance().getWorkInfoByIdLiveData(workerId)
            .observe(this, Observer<WorkInfo> {
                it?.let { workInfo ->
                    Log.d(TAG, "WorkStatus: $workInfo")
                }
            })
    }

    companion object {
        private const val TAG = "BasicActivity";
    }

}
