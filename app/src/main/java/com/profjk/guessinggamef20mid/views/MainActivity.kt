package com.profjk.guessinggamef20mid.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.profjk.guessinggamef20mid.R
import com.profjk.guessinggamef20mid.model.Score
import com.profjk.guessinggamef20mid.viewModel.ScoreViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() ,View.OnClickListener  {
    var correctNumber = 0
    var attempt = 5
    val TAG : String = this@MainActivity.toString()
    lateinit var scoreViewModel : ScoreViewModel

    companion object{
        var score = Score("Dana",false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreViewModel = ScoreViewModel(this.application)


        this.generateRandomNumber()

        btnCheck.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun generateRandomNumber() {
        correctNumber = Random().nextInt(25)
    }


    private fun validateData() : Boolean{
        if (edtAnswer.text.isEmpty()){
            edtAnswer.setError("Email cannot be empty")
            return false
        }

        return true
    }


    //when the btn is clicked we want to check if the num = random and validate
    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == edtAnswer.id) {
                if(this.validateData()) {
                    this.validateData()
                    attempt -=1
                    tvAttempts.text =attempt.toString()
                    this.playGame()
                }
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
                //generate a new random number and new 5 attempt as well as adding the attempt to the  :
                score.attemptResult= true;



            }
            alertBuilder.setNegativeButton("EXIT GAME"){ dialog, which ->
                //terminate the app :
                this@MainActivity.finish()

            }
        }
        else if (edtAnswer.text.toString().toInt() > correctNumber){
            Toast.makeText(this,"The number that you have entered is greater" ,
            Toast.LENGTH_SHORT).show()
        }

        else if (edtAnswer.text.toString().toInt() < correctNumber){
            Toast.makeText(this,"The number that you have entered is less than the correct answer" ,
                Toast.LENGTH_SHORT).show()
            //decrease the attempt by one
        }
    }


    fun playGame(){
        while (attempt!= 0){
            this.checkMatchNumber()
        }

        this.generateRandomNumber()

    }



    fun saveUserToDB(){
        try{
            scoreViewModel.insertAll(score)

        }catch (ex: Exception){
            Log.e(TAG, ex.toString())

        }
    }






}