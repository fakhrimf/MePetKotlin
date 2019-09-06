package com.chewie.mepet.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.chewie.mepet.entity.DetailProfileEntity
import com.chewie.mepet.entity.HomeEntity
import com.chewie.mepet.entity.ProfileEntity
import com.chewie.mepet.entity.ReminderEntity


@Database(entities = arrayOf(ProfileEntity::class,HomeEntity::class,DetailProfileEntity::class),version = 2)
abstract class mpDatabase : RoomDatabase() {
    abstract fun mepetDao():Dao companion object{
        private var INSTANCE:mpDatabase?=null

        fun getInstance(context: Context):mpDatabase?{
            if (INSTANCE == null){
                synchronized(mpDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),mpDatabase::class.java,"db_mepet").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}