package com.chewie.mepet.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.chewie.mepet.entity.DetailProfileEntity
import com.chewie.mepet.entity.HomeEntity
import com.chewie.mepet.entity.ProfileEntity
import com.chewie.mepet.entity.ReminderEntity


@Database(entities = arrayOf(ReminderEntity::class,ProfileEntity::class,HomeEntity::class,DetailProfileEntity::class),version = 1)
abstract class mpDatabase : RoomDatabase() {
}