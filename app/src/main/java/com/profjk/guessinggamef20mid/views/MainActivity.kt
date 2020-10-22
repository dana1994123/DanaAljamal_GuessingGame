package com.profjk.guessinggamef20mid.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.profjk.guessinggamef20mid.R
import java.util.*

class MainActivity : AppCompatActivity() {
    var correctNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun generateRandomNumber() {
        correctNumber = Random().nextInt(25)
    }
}