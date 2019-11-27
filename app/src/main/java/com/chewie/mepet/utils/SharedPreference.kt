package com.chewie.mepet.utils

import android.content.Context
import com.chewie.mepet.model.pet_profile

class SharedPreference(private var context: Context?) {


    fun setId(id:Int?){
        val sharedPreferences = context!!.getSharedPreferences(Constant.ID_PROFILE(),0)
        val edit = sharedPreferences!!.edit()
        edit.putInt("profile_id",id!!)
        edit.apply()
    }

    fun getId():Int{
        val sharedPreferences = context!!.getSharedPreferences(Constant.ID_PROFILE(),0)
        return sharedPreferences.getInt("profile_id",0)
    }

    fun setJamPagi(jam:String?){
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        val editor = sharedPreferences.edit()
        editor.putString("jam_pagi",jam)
        editor.apply()
    }
    fun getJamPagi():String{
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        return sharedPreferences.getString("jam_pagi","")
    }

    fun setJamSiang(jam:String?){
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        val editor = sharedPreferences.edit()
        editor.putString("jam_siang",jam)
        editor.apply()
    }
    fun getJamSiang():String{
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        return sharedPreferences.getString("jam_siang","")
    }

    fun setJamMalam(jam:String?){
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        val editor = sharedPreferences.edit()
        editor.putString("jam_malam",jam)
        editor.apply()
    }
    fun getJamMalam():String{
        val sharedPreferences = context!!.getSharedPreferences(Constant.JAM_PROFILE(),0)
        return sharedPreferences.getString("jam_malam","")
    }
}