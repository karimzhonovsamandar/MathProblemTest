package com.example.mathproblemtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathproblemtest.databinding.ActivityProblemBinding

class ProblemActivity : AppCompatActivity() {

    lateinit var binding: ActivityProblemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}