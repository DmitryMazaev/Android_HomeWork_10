package com.example.android_homework_10

import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import com.example.android_homework_10.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import kotlin.concurrent.thread
const val START_TEXT = "START"
const val STOP_TEXT = "STOP"
class MainActivity : AppCompatActivity() {
    private var progressCount: Int = 0
    private var count: Int = 0
    private var timer: CountDownTimer? = null
    private var timerStatus: Boolean = true
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                binding.count.text = binding.seekBar.progress.toString()
                binding.progressBar.progress = progress()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                binding.count.text = binding.seekBar.progress.toString()
                binding.progressBar.progress = progress()
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                binding.count.text = binding.seekBar.progress.toString()
                binding.progressBar.progress = progress()
            }
        })
        binding.buttonStart.setOnClickListener {
            if (timerStatus) {
                timerOn()
            }
            else {
                timerOff()
            }
        }
    }
    private fun timerOn() {
        timerStatus = false
        binding.buttonStart.text = STOP_TEXT
        timer?.cancel()
        binding.seekBar.isEnabled = false
        count = binding.seekBar.progress
        timer = object: CountDownTimer((count*1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.count.text = (millisUntilFinished/1000).toString()
                binding.seekBar.progress = (millisUntilFinished/1000).toInt()
                binding.progressBar.progress = ((millisUntilFinished*100)/(binding.seekBar.max*1000)).toInt()
            }
            override fun onFinish() {
                binding.count.text = binding.seekBar.progress.toString()
                binding.progressBar.progress = 0
                timerOff()
            }
        }.start()
    }
    private fun timerOff() {
        binding.seekBar.isEnabled = true
        timerStatus = true
        binding.buttonStart.text = START_TEXT
        timer!!.cancel()
    }
    private fun progress():Int {
        progressCount = binding.seekBar.progress*100/binding.seekBar.max
        return progressCount
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onStop() {
        super.onStop()
    }
}