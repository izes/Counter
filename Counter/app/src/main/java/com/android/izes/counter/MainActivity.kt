package com.android.izes.counter

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.izes.counter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var myNumber: MyNumber = MyNumber("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myNumber = myNumber

        binding.apply {
            btnCountDown.setOnClickListener {minusOne()}
            btnCountUp.setOnClickListener {plusOne()}
            btnToast.setOnClickListener {toast()}
        }
    }

    private fun plusOne() {
        var myInt = (Integer.parseInt(myNumber.number) + 1).toString()
        binding.textView.text = myInt
    }

    private fun minusOne() {
        var myInt = (Integer.parseInt(myNumber.number) - 1).toString()
        binding.textView.text = myInt
    }

    private fun toast() {
        Toast.makeText(this, "My current number is ${binding.textView.text}", Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("myNumber", binding.textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
         savedInstanceState.let {
             myNumber.number = it.getString("myNumber").toString()
         }
    }
}