package com.lomovskiy.ipcserver

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Process

class IPCServerService : Service() {

    companion object {

        var connectionCount: Int = 0

        val emptyClientData = "empty client data"

    }

    private val binder: Binder = object : IPCServer.Stub() {

        override fun getPid(): Int {
            return Process.myPid()
        }

        override fun getConnectionCount(): Int {
            return IPCServerService.connectionCount
        }

        override fun setDisplayedValue(packageName: String, pid: Int, data: String?) {
            val clientData = data ?: emptyClientData
            val client = Client(
                packageName = packageName,
                pid = pid,
                data = clientData,
                ipcMethod = "AIDL"
            )
            RecentClient.client = client
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        connectionCount.inc()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        RecentClient.client = null
        return super.onUnbind(intent)
    }

}
