package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var editTextFirstNumber: EditText
    private lateinit var editTextSecondNumber: EditText
    private lateinit var editTextResult: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        initializeViews()

        // Set up button click listeners
        setupClickListeners()
    }

    private fun initializeViews() {
        editTextFirstNumber = findViewById(R.id.editTextText)
        editTextSecondNumber = findViewById(R.id.editTextText2)
        editTextResult = findViewById(R.id.editTextText3)
        buttonAdd = findViewById(R.id.button1)
        buttonSubtract = findViewById(R.id.button2)
        buttonMultiply = findViewById(R.id.button3)
        buttonDivide = findViewById(R.id.button4)

        // Make result field non-editable
        editTextResult.isEnabled = false
    }

    private fun setupClickListeners() {
        buttonAdd.setOnClickListener {
            performCalculation(Operation.ADD)
        }

        buttonSubtract.setOnClickListener {
            performCalculation(Operation.SUBTRACT)
        }

        buttonMultiply.setOnClickListener {
            performCalculation(Operation.MULTIPLY)
        }

        buttonDivide.setOnClickListener {
            performCalculation(Operation.DIVIDE)
        }
    }

    private fun performCalculation(operation: Operation) {
        val num1Str = editTextFirstNumber.text.toString()
        val num2Str = editTextSecondNumber.text.toString()

        // Validate input
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            showToast("Please enter both numbers")
            return
        }

        try {
            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()
            val result: Double

            when (operation) {
                Operation.ADD -> result = num1 + num2
                Operation.SUBTRACT -> result = num1 - num2
                Operation.MULTIPLY -> result = num1 * num2
                Operation.DIVIDE -> {
                    if (num2 == 0.0) {
                        showToast("Cannot divide by zero")
                        editTextResult.setText("Error")
                        return
                    }
                    result = num1 / num2
                }
            }

            // Display result
            editTextResult.setText(String.format("%.2f", result))

        } catch (e: NumberFormatException) {
            showToast("Please enter valid numbers")
            editTextResult.setText("Error")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Enum to represent different operations
    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}