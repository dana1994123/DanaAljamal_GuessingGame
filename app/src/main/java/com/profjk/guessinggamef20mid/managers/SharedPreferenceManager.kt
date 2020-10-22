package com.profjk.guessinggamef20mid.managers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {


    private var sharedPreferences: SharedPreferences? = null

    val ATTEMPT = "KEY_ATTEMPT"



    fun init (context: Context) {
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.packageName,
                Activity.MODE_PRIVATE)
        }
    }


    fun write(key: String?, value: String?){
        apply { sharedPreferences!!.edit().putString(key, value).apply() }
    }


    fun read(key: String?, defaultValue: String?): String?{

        with(sharedPreferences) {
            if (this!!.contains(key)){
                return sharedPreferences!!.getString(key,defaultValue)
            }
        }
        return defaultValue
    }


    fun remove(key: String?){

        if ( sharedPreferences != null && sharedPreferences!!.contains(key)) {
            apply { sharedPreferences?.edit()!!.remove(key).apply() }
        }
    }

    fun removeAll(){
        with(sharedPreferences!!.edit()){
            remove(ATTEMPT)

            apply()
        }
    }




}