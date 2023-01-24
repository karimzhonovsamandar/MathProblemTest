package com.example.mathproblemtest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.mathproblemtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numberFormat()
        onProgress()

        binding.checkButton.setOnClickListener {
            nullCheck()
            login()
        }

    }

    fun nullCheck() {

        if ( binding.loginEditText.text.toString() == "") {
            binding.loginLayoutEditText.helperText = "Заполните это поле!!!"
        }
        if ( binding.passwordEditText.text.toString() == "") {
            binding.passwordLayoutEditText.helperText = "Заполните это поле!!!"
        }

    }

    @SuppressLint("ResourceAsColor")
    fun numberFormat() {

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {

            var isBackspaceClicked = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isBackspaceClicked = after < count
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!isBackspaceClicked) {

                    /* Let me prepare a StringBuilder to hold all digits of the edit text */
                    val digits = StringBuilder();

                    /* this is the phone StringBuilder that will hold the phone number */

                    val phone = StringBuilder();

                    /* let's take all characters from the edit text */
                    val chars: CharArray = binding.passwordEditText.text.toString().toCharArray();

                    /* a loop to extract all digits */
                    for (x in chars.indices) {
                        if (Character.isDigit(chars[x])) {
                            /* if its a digit append to digits string builder */
                            digits.append(chars[x]);
                        }
                    }


                    if (digits.toString().length >= 2) {
                        /* our phone formatting starts at the third character  and starts with the country code*/
                        var countryCode = String();

                        /* we build the country code */
                        countryCode += "(" + digits.toString().substring(0, 2) + ") ";

                        /** and we append it to phone string builder **/
                        phone.append(countryCode);

                        /** if digits are more than or just 6, that means we already have our state code/region code **/
                        if (digits.toString().length >= 5) {

                            var firstThree = String();
                            /** we build the state/region code **/
                            firstThree += digits.toString().substring(2, 5) + "-";
                            /** we append the region code to phone **/
                            phone.append(firstThree);

                            /** the phone number will not go over 12 digits  if ten, set the limit to ten digits**/
                            if (digits.toString().length >= 7) {
                                var firstTwoNumbers = String()
                                firstTwoNumbers += digits.toString().substring(5, 7) + "-"
                                phone.append(firstTwoNumbers)

                                if (digits.toString().length >= 9) {
                                    phone.append(digits.toString().substring(7, 9))
                                } else {
                                    phone.append(digits.toString().substring(7));
                                }
                            } else {
                                phone.append(digits.toString().substring(5));
                            }

                        } else {
                            phone.append(digits.toString().substring(2));
                        }
                        /** remove the watcher  so you can not capture the affectation you are going to make, to avoid infinite loop on text change **/
                        binding.passwordEditText.removeTextChangedListener(this);
                        /** set the new text to the EditText **/
                        binding.passwordEditText.setText(phone.toString());
                        /** bring the cursor to the end of input **/
                        binding.passwordEditText.setSelection(binding.passwordEditText.text.toString().length);
                        /* bring back the watcher and go on listening to change events */
                        binding.passwordEditText.addTextChangedListener(this);

                    } else {
                        return;
                    }

                } else {
                    return
                }
            }

        })

    }


    @SuppressLint("ResourceAsColor")
    fun onProgress() {
        binding.loginEditText.doOnTextChanged { text, start, before, count ->
            if (text == "") {
                binding.loginLayoutEditText.helperText = "Заполните это поле!!!"
            }
            else if (text!!.isNotEmpty()){
                binding.loginLayoutEditText.helperText = ""
            }
            else{
                binding.loginLayoutEditText.helperText = "Заполните это поле!!!"
            }

        }



        binding.passwordEditText.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()){
                binding.passwordLayoutEditText.helperText = ""
            }
            if (text.length < 14) {
                binding.passwordLayoutEditText.boxStrokeColor = Color.parseColor("#FF0000")
            }
            else if (text.length == 14){
                binding.passwordLayoutEditText.boxStrokeColor = Color.parseColor("#16CD1D")
            }

        }
    }

    fun login() {
        if (binding.loginEditText.text.toString().trim() == "Samandar" //
            &&
            binding.passwordEditText.text.toString().trim() == "(90) 408-74-24" ) {  // 90 408 74-24
            startActivity(Intent(this, ProblemActivity::class.java))
        }

        else{
            Toast.makeText(this, "логин или пароль неверный", Toast.LENGTH_SHORT).show()
        }

    }
}