package com.ywalakamar.geo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.ywalakamar.geo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Reference widgets*/
        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)

        /*Setting listeners*/
        trueButton.setOnClickListener { view:View->
            //Do something in response to click
        }

        falseButton.setOnClickListener { view:View->
            //Do something in response to click
        }
    }
}
