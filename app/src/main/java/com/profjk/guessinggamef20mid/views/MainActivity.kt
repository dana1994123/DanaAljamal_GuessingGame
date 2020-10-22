package com.profjk.guessinggamef20mid.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.profjk.guessinggamef20mid.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() ,View.OnClickListener  {
    var correctNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //generate a random number and save it so user ca guess wtis it :
        this.generateRandomNumber()

        //allow 5 attempts to the user to see if it is right
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun generateRandomNumber() {
        correctNumber = Random().nextInt(25)
    }


    fun dataValidation(){
        if (edtAnswer.text.isEmpty()){
            edtAnswer.error = "Please Enter Your Answer"
        }
    }


    //when the btn is clicked we want to check if the num = random and validate
    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == edtAnswer.id) {
                this.dataValidation()
                this.checkMatchNumber()
            }
        }
    }


    fun checkMatchNumber(){
        //check if the generated num == number added by user
        if (edtAnswer.text.toString().toInt() == correctNumber){
            //alert showing if he wins winner
            val alertBuilder = AlertDialog.Builder(this)
            Toast.makeText(this, "Correct. You win", Toast.LENGTH_LONG).show()

            alertBuilder.setTitle("Result")
            alertBuilder.setMessage("Great! You won the game. Do You have to play again ?")
            alertBuilder.setPositiveButton("PLAY AGAIN"){ dialog, which ->

            }

            alertBuilder.setNegativeButton("EXIT GAME"){ dialog, which ->

            }


        }
        else if (edtAnswer.text.toString().toInt() > correctNumber){
            Toast.makeText(this,"The number that you have entered is greater" ,
            Toast.LENGTH_SHORT).show()
        }
        else if (edtAnswer.text.toString().toInt() < correctNumber){
            Toast.makeText(this,"The number that you have entered is less than the correct answer" ,
                Toast.LENGTH_SHORT).show()

        }
    }



}