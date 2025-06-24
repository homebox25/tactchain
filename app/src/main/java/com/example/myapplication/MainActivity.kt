package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.tactchain.model.Asset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class MainActivity : ComponentActivity(), View.OnClickListener {
    lateinit var searchBttn : Button
    lateinit var textbox: TextView
    lateinit var textDetails: TextView
    lateinit var textLatency : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchBttn = findViewById<Button>(R.id.button)
        textbox = findViewById<TextView>(R.id.textbox)
        textDetails = findViewById<TextView>(R.id.textDetails)
        textLatency = findViewById<TextView>(R.id.textLatency)

        searchBttn.setOnClickListener(this)
        enableEdgeToEdge()
//        setContent {
//            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button -> {
                val asset = Asset("1", "Active")
                sendAssetToServer(asset)

            }
        }
    }

    fun sendAssetToServer(asset: Asset) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val socket = Socket("10.0.2.2", 8080) // Replace with your PC's LAN IP
                val writer = PrintWriter(socket.getOutputStream(), true)
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                val json = """{"assetId":"${asset.assetId}","status":"${asset.status}"}"""
                writer.println(json)

                val response = reader.readLine()
                Log.d("TCP", "Server response: $response")

                socket.close()
            } catch (e: Exception) {
                Log.e("TCP", "Error sending to server", e)
            }
        }
    }

}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}