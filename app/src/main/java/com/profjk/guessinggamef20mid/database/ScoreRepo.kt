package com.profjk.guessinggamef20mid.database

import androidx.lifecycle.LiveData
import com.profjk.guessinggamef20mid.model.Score

class ScoreRepo(private val scoreDao: ScoreDao) {



        val allScore: LiveData<List<Score>> = scoreDao.getAllScores()

        suspend fun insertAll(score: Score){
            scoreDao.insertAll(score)
        }

        suspend fun updateUser(score: Score){
            scoreDao.updateScore(score)
        }

}