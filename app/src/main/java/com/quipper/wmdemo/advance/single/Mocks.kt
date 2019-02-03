package com.quipper.wmdemo.advance.single

import android.content.Context
import androidx.work.Data


object AppPreferences {
    fun getSecretKey(): String {
        return ""
    }
}

object UserPreferences {
    fun getUserData(): Array<String> {
        return arrayOf()
    }
}

class MockNetworkCall {
    companion object {
        fun initializeWithParameters(context: Context, secretKey: String) {
        }

        fun doLongRunningNetworkCall(userData: Array<String>?): NetworkCallResult {
            return NetworkCallResult()
        }
    }
}

class NetworkCallResult {
    fun toOutputData(): Data {
        return Data.EMPTY
    }
}