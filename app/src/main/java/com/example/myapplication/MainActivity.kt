package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

class MainActivity : ComponentActivity(), View.OnClickListener {
    lateinit var searchBttn : Button
    lateinit var textbox: EditText
    lateinit var textDetails: EditText
    lateinit var textLatency : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchBttn = findViewById<Button>(R.id.button)
        textbox = findViewById<EditText>(R.id.textbox)
        textDetails = findViewById<EditText>(R.id.textDetails)
        textLatency = findViewById<EditText>(R.id.textLatency)

        searchBttn.setOnClickListener(this)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onClick(v: View?) {
        var tag = textbox.text.toString()
        when(v?.id)
        {
            R.id.button -> {
                //logic to pull from our database goes here
                //textDetails.text = "Details: " + pulled info
                //textLatency.text = "Latency: " + latency checl

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}