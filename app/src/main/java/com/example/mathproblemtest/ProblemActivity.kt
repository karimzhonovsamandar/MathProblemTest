package com.example.mathproblemtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathproblemtest.databinding.ActivityProblemBinding
import kotlin.random.Random

class ProblemActivity : AppCompatActivity() {

    lateinit var binding: ActivityProblemBinding

    var x1 = 0
    var y1 = 0
    var userAnswer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onRandom()

        binding.checkButton.setOnClickListener {

        }


    }

    fun onRandom(){
        var x = Random.nextInt(89)+10
        var y = Random.nextInt(89)+10

        x1 = x
        y1 = y

        binding.firstNumber.text = x1.toString()
        binding.secondNumber.text = y1.toString()
    }

    fun onCheck(){
        val result = x1+y1

    }


}