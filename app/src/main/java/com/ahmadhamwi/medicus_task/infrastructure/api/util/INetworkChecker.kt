package com.ahmadhamwi.medicus_task.infrastructure.api.util

import android.content.Context
import android.net.ConnectivityManager

interface INetworkChecker {
    fun isInternetAvailable(): Boolean
}