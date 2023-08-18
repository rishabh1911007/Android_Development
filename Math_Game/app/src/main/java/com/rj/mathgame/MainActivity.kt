package com.rj.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition: Button
    lateinit var subtraction: Button
    lateinit var multiplication: Button
    lateinit var division: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition= findViewById(R.id.add)
        subtraction= findViewById(R.id.sub)
        multiplication= findViewById(R.id.multi)
        division= findViewById(R.id.div)

        addition.setOnClickListener {
            val intent= Intent(this@MainActivity, AddGameActivity::class.java)
            startActivity(intent)
        }
        subtraction.setOnClickListener {
            val intent= Intent(this@MainActivity, SubGameActivity::class.java)
            startActivity(intent)
        }
        multiplication.setOnClickListener {
            val intent= Intent(this@MainActivity, MultiGameActivity::class.java)
            startActivity(intent)
        }
        division.setOnClickListener {
            val intent= Intent(this@MainActivity, DivGameActivity::class.java)
            startActivity(intent)
        }

    }
}