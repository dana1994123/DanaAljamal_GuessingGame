package com.profjk.guessinggamef20mid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.profjk.guessinggamef20mid.R
import com.profjk.guessinggamef20mid.model.Score

@Database(entities = arrayOf(Score::class), version = 1)
abstract class ScoreDB: RoomDatabase() {

    abstract fun scoreDao(): ScoreDao
    companion object{

        @Volatile
        private var INSTANCE: ScoreDB? = null

        fun getDatabase(context: Context) : ScoreDB {
            val tempInstance = INSTANCE

            //if there is already some instance of the database, return that
            if (tempInstance != null){
                return tempInstance
            }

                //otherwise, create new database
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScoreDB::class.java,
                        R.string.database_name.toString()
                ).build()

                INSTANCE = instance
                return instance
                }

            }
        }
    }
