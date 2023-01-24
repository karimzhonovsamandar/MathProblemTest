package com.example.mathproblemtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.mathproblemtest.databinding.ActivityResultBinding
import java.net.BindException

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val answer = intent.getStringExtra("true") //Yes
        val rightNumber = intent.getStringExtra("rightNumber")
        if (answer == "Yes") {
            binding.wonTextView.isVisible = true // type 1
        } else {
            binding.loseTextView.isVisible = true //вы проиграли
            binding.rightAnswerText.isVisible = true // правильный ответ

            /*binding.rightAnswerNumber.visibility = View.VISIBLE */// type 2

            binding.rightAnswerNumber.text = rightNumber
        }


    }
}