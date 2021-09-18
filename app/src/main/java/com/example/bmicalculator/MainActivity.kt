package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val imageboy =findViewById<ImageView>(R.id.image_boy)
        val imagegirl =findViewById<ImageView>(R.id.image_girl)
        val weight =findViewById<EditText>(R.id.weight_value)
        val height =findViewById<EditText>(R.id.height_value)
        val calculatebutton =findViewById<Button>(R.id.calculate_button)
        val bmi=findViewById<TextView>(R.id.bmi)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val bmiView = findViewById<LinearLayout>(R.id.bmiView)
        val calculateAgainButton = findViewById<TextView>(R.id.calculate_again)

        imageboy.setOnClickListener {
            imageboy.setImageResource(R.drawable.boy)
            imagegirl.setImageResource(R.drawable.female)

        }
        imagegirl.setOnClickListener {
            imageboy.setImageResource(R.drawable.boy)
            imagegirl.setImageResource(R.drawable.female)
        }
        calculatebutton.setOnClickListener {
            var weightvalue =0.0
            var heightvalue =0.0
            if(weight.text.toString().isNotEmpty()){
                weightvalue = weight.text.toString().toDouble()

            }
            if(height.text.toString().isNotEmpty()){
                heightvalue = (height.text.toString().toDouble()/100)

            }
            if(weightvalue > 0.0 && heightvalue>0.0){
                val bmiValue = String.format("%.2f",weightvalue/heightvalue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightvalue / heightvalue.pow(2))
                bmiView.visibility= VISIBLE
                calculateAgainButton.visibility= VISIBLE

            }
            else
                Toast.makeText(this, "Please Enter your Weight & Height (Greater Than 0)", Toast.LENGTH_SHORT).show()
        }
        calculateAgainButton.setOnClickListener{
            bmiView.visibility= GONE
            calculatebutton.visibility= VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }
    }
    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Obesity"
        return bmiStatus
    }
}