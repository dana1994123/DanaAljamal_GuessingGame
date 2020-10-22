package com.profjk.guessinggamef20mid.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.profjk.guessinggamef20mid.database.ScoreDB
import com.profjk.guessinggamef20mid.database.ScoreRepo
import com.profjk.guessinggamef20mid.model.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {
    private val scoreRepo: ScoreRepo
    var allScores: LiveData<List<Score>>

    private var matchedUser : MutableLiveData<Score>?

    init {
        val scoreDao = ScoreDB.getDatabase(application).scoreDao()
        scoreRepo =  ScoreRepo(scoreDao)

        allScores = scoreRepo.allScore

        matchedUser = MutableLiveData()
    }

    /**
     * insertAll() method will create a new user record in the database
     */
    fun insertAll(score : Score) = viewModelScope.launch(Dispatchers.IO){
        scoreRepo.insertAll(score)
    }

    fun updateUser(score : Score) = viewModelScope.launch(Dispatchers.IO) {
        scoreRepo.updateUser(score)
    }


}