package com.lomovskiy.ipcserver

import android.os.Bundle
import android.os.Process
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lomovskiy.ipcserver.ui.theme.AndroidipcserverTheme

data class Client(
    val packageName: String,
    val pid: Int,
    val data: String?,
    val ipcMethod: String
) {

    companion object {

        fun stub(): Client {
            return Client(
                packageName = "com.lomovskiy.ipcserver",
                pid = Process.myPid(),
                data = "data",
                ipcMethod = "ipcMethod"
            )
        }

    }

}

object RecentClient {

    var client: Client? = null

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidipcserverTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ClientView(client = Client.stub())
                }
            }
        }
    }
}

@Composable
fun ClientView(client: Client) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = client.packageName)
        Text(text = client.pid.toString())
        Text(text = client.data ?: "empty")
        Text(text = client.ipcMethod)
    }

}

@Preview(showBackground = true)
@Composable
fun ClientViewPreview(client: Client = Client.stub()) {
    ClientView(client = client)
}