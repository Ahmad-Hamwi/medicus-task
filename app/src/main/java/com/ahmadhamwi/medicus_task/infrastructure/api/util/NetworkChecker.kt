package com.ahmadhamwi.medicus_task.infrastructure.api.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkChecker(val context: Context): INetworkChecker {

    override fun isInternetAvailable(): Boolean {
        val mConMgr: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (mConMgr.activeNetworkInfo != null && mConMgr.activeNetworkInfo!!.isAvailable
                && mConMgr.activeNetworkInfo!!.isConnected)
    }
}