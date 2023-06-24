package com.chugunov.foodapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "menu.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }

    abstract fun foodItemDao(): FoodItemDao
}