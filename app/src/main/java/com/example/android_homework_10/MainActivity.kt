package com.example.android_homework_10

import android.os.Bundle
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
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var count: Int = 0
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
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                binding.count.text = binding.seekBar.progress.toString()
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                binding.count.text = binding.seekBar.progress.toString()
            }
        })
        binding.buttonStart.setOnClickListener {
            timerOn()

        }
    }
    private fun timerOn() {
        //binding.seekBar.isEnabled = false
        count = binding.seekBar.progress
        thread {
           while (count >= 0) {
                sleep(1000)
                count -=1
                binding.seekBar.progress = count
           }
           //binding.seekBar.isEnabled = true
        }

    }

}