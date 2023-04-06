package com.example.erkinbekovbilimdz_all_4.data.local

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.MODE_PRIVATE
import androidx.constraintlayout.motion.widget.KeyTimeCycle
import java.security.cert.CertPath

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)


    fun isUserSeen() : Boolean{
        return pref.getBoolean(ONBOARD_KEY, false)
    }


    fun saveSeen(){
        pref.edit().putBoolean(ONBOARD_KEY, true).apply()
    }

    fun saveNameText(text : String){
        pref.edit().putString(KEY_FOR_NAME, text).apply()
    }

    fun getNameText() = pref.getString(KEY_FOR_NAME, "")


    fun saveImage(image: String){
        pref.edit().putString(KEY_FOR_IMAGE, image).apply()
    }

    fun getImage() : String{
        return pref.getString(KEY_FOR_IMAGE, "").toString()
    }


    companion object{
        const val PREF_NAME = "task.name"
        const val KEY_FOR_NAME = "textName"
        const val ONBOARD_KEY = "onBoardKey"
        const val KEY_FOR_IMAGE = "saveImage"
    }
}