package com.example.mathproblemtest


import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mathproblemtest.databinding.ActivityProblemBinding
import kotlin.random.Random


class ProblemActivity : AppCompatActivity() {

    lateinit var binding: ActivityProblemBinding

    var x1 = 0
    var y1 = 0
    var userAnswer = 0
    var resultGlobal = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onRandom()

        binding.checkButton.setOnClickListener {

            if (binding.answerEditText.text.toString() == "") {
                shake()
            } else {
                userAnswer = binding.answerEditText.text.toString().toInt() //55
                if (userAnswer != resultGlobal) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("true", "No")
                    intent.putExtra("rightNumber", resultGlobal.toString())
                    startActivity(intent)
                    shake()
                     errorAnswer()
                } else {
                    onCheck()
                }
            }

        }
    }

    fun onRandom() {
        var x = Random.nextInt(89) + 10 //15
        var y = Random.nextInt(89) + 10 //32

        x1 = x
        y1 = y

        val result = x + y
        resultGlobal = result

        binding.firstNumber.text = x1.toString()
        binding.secondNumber.text = y1.toString()

    }

    fun onCheck() {

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("true", "Yes")
        startActivity(intent)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun errorAnswer() {
        val colorInt: Int = baseContext.getColor(R.color.mtrl_textinput_default_box_stroke_color1)
        binding.answerLayoutEditText.hintTextColor = ColorStateList.valueOf(colorInt)
    }

    fun shake() {
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.anim)
        binding.answerLayoutEditText.startAnimation(shake)
    }


}