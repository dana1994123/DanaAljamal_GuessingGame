package com.profjk.guessinggamef20mid.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.profjk.guessinggamef20mid.model.Score

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg users: Score)

    @Update
    fun updateScore(vararg score: Score)


    @Query("SELECT * FROM Score")
    fun getAllScores(): LiveData<List<Score>>


}



