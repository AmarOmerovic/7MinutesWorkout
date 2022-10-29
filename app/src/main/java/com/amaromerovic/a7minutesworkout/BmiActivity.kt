package com.amaromerovic.a7minutesworkout

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.a7minutesworkout.databinding.ActivityBmiBinding
import com.amaromerovic.a7minutesworkout.util.Constants

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.calculate.setOnClickListener {
            val weightValue: Double
            val heightValue: Double
            val feetValue: Double
            val inchValue: Double

            if (binding.weightLayout.visibility == View.VISIBLE && binding.weight.text?.isEmpty() == true) {
                binding.weightLayout.isErrorEnabled = true
                binding.weightLayout.error = "Field is empty!"
            } else {
                binding.weightLayout.isErrorEnabled = false
            }

            if (binding.heightLayout.visibility == View.VISIBLE && binding.height.text?.isEmpty() == true) {
                binding.heightLayout.isErrorEnabled = true
                binding.heightLayout.error = "Field is empty!"
            } else {
                binding.heightLayout.isErrorEnabled = false
            }

            if (binding.feetLayout.visibility == View.VISIBLE && binding.inchLayout.visibility == View.VISIBLE) {
                if (binding.feet.text?.isEmpty() == true) {
                    binding.feetLayout.isErrorEnabled = true
                    binding.feetLayout.error = "Field is empty!"
                } else {
                    binding.feetLayout.isErrorEnabled = false
                }

                if (binding.inch.text?.isEmpty() == true) {
                    binding.inchLayout.isErrorEnabled = true
                    binding.inchLayout.error = "Field is empty!"
                } else {
                    binding.inchLayout.isErrorEnabled = false
                }
            }


            if (((binding.weight.text?.isEmpty() == false) && (binding.height.text?.isEmpty() == false) && binding.metricUnits.isChecked) ||
                ((binding.weight.text?.isEmpty() == false) && (binding.feet.text?.isEmpty() == false) && (binding.inch.text?.isEmpty() == false) && binding.usUnits.isChecked)
            ) {

                weightValue = binding.weight.text.toString().toDouble()
                var bmi = 0.0
                if (binding.metricUnits.isChecked) {
                    heightValue = binding.height.text.toString().toDouble()
                    bmi = weightValue / ((heightValue / 100) * (heightValue / 100))
                    binding.bmiCalculation.text =
                        String.format("%.2f", bmi)
                } else if (binding.usUnits.isChecked) {
                    feetValue = binding.feet.text.toString().toDouble()
                    inchValue = binding.inch.text.toString().toDouble()
                    val kg = weightValue * Constants.POUND_VALUE
                    val heightFromFeedAndInch = ((feetValue * 12) + inchValue) * 2.54
                    bmi =
                        kg / ((heightFromFeedAndInch / 100) * (heightFromFeedAndInch / 100))
                    binding.bmiCalculation.text =
                        String.format("%.2f", bmi)
                }



                if (bmi < 18.5) {
                    binding.bmiState.text = String.format("Underweight")
                    binding.bmiDescription.text =
                        String.format("Oops!\nYou really need to take better care of yourself!\nEat more!")
                } else if (bmi in 18.5..24.9) {
                    binding.bmiState.text = String.format("Normal")
                    binding.bmiDescription.text =
                        String.format("Congratulations!\nYou are in a good shape!")
                } else if (bmi in 25.0..29.9) {
                    binding.bmiState.text = String.format("Overweight")
                    binding.bmiDescription.text =
                        String.format("Oops!\nYou really need to take care of your yourself!\nWorkout maybe!")
                } else if (bmi in 30.0..34.9) {
                    binding.bmiState.text = String.format("Obesity (Class 1)")
                    binding.bmiDescription.text =
                        String.format("Oops!\nYou really need to take care of your yourself!\nWorkout maybe!")
                } else if (bmi in 35.0..39.9) {
                    binding.bmiState.text = String.format("Obesity (Class 2)")
                    binding.bmiDescription.text =
                        String.format("OMG!\nYou are in a very dangerous condition!\nAct now!")
                } else if (bmi >= 40) {
                    binding.bmiState.text = String.format("Extreme Obesity")
                    binding.bmiDescription.text =
                        String.format("OMG!\nYou are in a very dangerous condition!\nAct now!")
                }

                if (binding.yourBmiText.visibility == View.GONE && binding.bmiCalculation.visibility == View.GONE && binding.bmiState.visibility == View.GONE && binding.bmiDescription.visibility == View.GONE) {
                    binding.yourBmiText.visibility = View.VISIBLE
                    binding.bmiCalculation.visibility = View.VISIBLE
                    binding.bmiState.visibility = View.VISIBLE
                    binding.bmiDescription.visibility = View.VISIBLE
                }
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _: RadioGroup, i: Int ->
            if (i == R.id.metricUnits) {
                showMetricUnitsOnly()
                binding.weightLayout.hint = String.format("WEIGHT (in kg)")
            } else {
                showUsUnitsOnly()
                binding.weightLayout.hint = "WEIGHT (in pounds)"
            }

            if (binding.yourBmiText.visibility == View.VISIBLE && binding.bmiCalculation.visibility == View.VISIBLE && binding.bmiState.visibility == View.VISIBLE && binding.bmiDescription.visibility == View.VISIBLE) {
                binding.yourBmiText.visibility = View.GONE
                binding.bmiCalculation.visibility = View.GONE
                binding.bmiState.visibility = View.GONE
                binding.bmiDescription.visibility = View.GONE
            }

            binding.weightLayout.isErrorEnabled = false
            binding.heightLayout.isErrorEnabled = false
            binding.feetLayout.isErrorEnabled = false
            binding.inchLayout.isErrorEnabled = false

            binding.weight.text = null
            binding.height.text = null
            binding.inch.text = null
            binding.feet.text = null
        }

    }

    private fun showUsUnitsOnly() {
        if (binding.feetLayout.visibility == View.GONE) {
            binding.feetLayout.visibility = View.VISIBLE
        }

        if (binding.inchLayout.visibility == View.GONE) {
            binding.inchLayout.visibility = View.VISIBLE
        }

        if (binding.heightLayout.visibility == View.VISIBLE) {
            binding.heightLayout.visibility = View.GONE
        }

    }

    private fun showMetricUnitsOnly() {
        if (binding.heightLayout.visibility == View.GONE) {
            binding.heightLayout.visibility = View.VISIBLE
        }

        if (binding.feetLayout.visibility == View.VISIBLE) {
            binding.feetLayout.visibility = View.GONE
        }

        if (binding.inchLayout.visibility == View.VISIBLE) {
            binding.inchLayout.visibility = View.GONE
        }
    }
}