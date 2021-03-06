package com.profjk.guessinggamef20mid.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.profjk.guessinggamef20mid.R
import com.profjk.guessinggamef20mid.managers.SharedPreferenceManager
import com.profjk.guessinggamef20mid.model.Score
import com.profjk.guessinggamef20mid.scorefragment.ScoreFragment
import com.profjk.guessinggamef20mid.viewModel.ScoreViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() ,View.OnClickListener  {
    var correctNumber = 0
    var attempt = 5
    val TAG : String = this@MainActivity.toString()
    lateinit var scoreViewModel : ScoreViewModel
    var point =0
    private lateinit var navController: NavController

    companion object{
        var score = Score("Dana",false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreViewModel = ScoreViewModel(this.application)



        btnCheck.setOnClickListener(this)
        this.generateRandomNumber()



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //navView.setupWithNavController(navController)



    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_home, menu)
            return super.onCreateOptionsMenu(menu)


    }

    private fun generateRandomNumber() {
        correctNumber = Random().nextInt(25)
    }


    private fun validateData() : Boolean{
        if (edtAnswer.text.isEmpty()){
            edtAnswer.error = "the answer cannot be empty "
            return false
        }
        return true
    }


    //when the btn is clicked we want to check if the num = random and validate
    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == btnCheck.id) {
                if(this.validateData()) {
                    //decrement the attempt
                    attempt -=1
                    //check if the answer is right
                    this.checkMatchNumber()
                    edtAnswer.setText("")
                    tvAttempts.text = "the remaining attempt is ${attempt}"
                }
            }
        }
    }


    fun checkMatchNumber(){
        //check if the generated num == number added by user
        if ((edtAnswer.text.toString().toInt() == correctNumber)|| (attempt == 0 )){
            //add the remaining attempt to the point
            if (attempt != 0) {
                point += attempt

                //save the score to the DB
                this.saveScoreToDB()
            }

            //alert showing if he win and to redo the game :
            val alertBuilder = AlertDialog.Builder(this)
            Toast.makeText(this, "Correct. You win", Toast.LENGTH_LONG).show()
            alertBuilder.setTitle("Result")
            alertBuilder.setMessage("Great! You won the game. Do You have to play again ?")


            alertBuilder.setPositiveButton("PLAY AGAIN"){ dialog, which ->
                //generate a new random number and new 5 attempt as well as adding the attempt to the  :
                attempt = 5
                tvAttempts.setText("")
                this.generateRandomNumber()
            }

            alertBuilder.setNegativeButton("EXIT GAME"){ dialog, which ->
                //terminate the app :
                this@MainActivity.finishAffinity()

            }
            alertBuilder.show()
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


    fun saveScoreToDB(){
        try{
            //chage the attempt result and save the score to the db
            score.attemptResult = true
            scoreViewModel.insertAll(score)

        }catch (ex: Exception){
            Log.e(TAG, ex.toString())

        }
    }






}