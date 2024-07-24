package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var seconds = 0
    private var running = false
    private var lastRunningState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            lastRunningState = savedInstanceState.getBoolean("lastRunningState")
        }

        runStopwatch()
    }

    private fun runStopwatch() {
        val txtTime: TextView = findViewById(R.id.txttime)
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                txtTime.text = time

                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    fun onClickStart(view: View) {
        running = true
    }

    fun onClickStop(view: View) {
        running = false
    }

    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("lastRunningState", lastRunningState)
    }
}
