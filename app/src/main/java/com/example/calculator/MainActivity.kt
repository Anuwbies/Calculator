package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var num1st: EditText
    private lateinit var num2nd: EditText
    private lateinit var answer: TextView
    private lateinit var addBtn: Button
    private lateinit var subBtn: Button
    private lateinit var mulBtn: Button
    private lateinit var divBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1st = findViewById(R.id.num1st)
        num2nd = findViewById(R.id.num2nd)
        answer = findViewById(R.id.answer)

        addBtn = findViewById(R.id.addBtn)
        subBtn = findViewById(R.id.subBtn)
        mulBtn = findViewById(R.id.mulBtn)
        divBtn = findViewById(R.id.divBtn)

        // Set onClickListeners for each operation button
        addBtn.setOnClickListener { performOperation("add") }
        subBtn.setOnClickListener { performOperation("subtract") }
        mulBtn.setOnClickListener { performOperation("multiply") }
        divBtn.setOnClickListener { performOperation("divide") }

        num1st.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                num1st.hint = ""
            } else {
                if (num1st.text.isEmpty()) {
                    num1st.hint = "Input a Number"
                }
            }
        }

        num2nd.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                num2nd.hint = ""
            } else {
                if (num2nd.text.isEmpty()) {
                    num2nd.hint = "Input a Number"
                }
            }
        }
    }

    // Method to perform the calculation
    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun performOperation(operation: String) {
        val input1 = num1st.text.toString()
        val input2 = num2nd.text.toString()

        // Validate input
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = input1.toDouble()
        val num2 = input2.toDouble()
        var result = 0.0

        // Perform the selected operation
        when (operation) {
            "add" -> result = num1 + num2
            "subtract" -> result = num1 - num2
            "multiply" -> result = num1 * num2
            "divide" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                result = num1 / num2
            }
        }

        // Format the result to display without decimals if it's an integer
        val formattedResult = if (result % 1 == 0.0) {
            result.toInt().toString()  // Display as integer if no decimal part
        } else {
            String.format("%.2f", result)
        }

        answer.text = "Answer: $formattedResult"
    }
}