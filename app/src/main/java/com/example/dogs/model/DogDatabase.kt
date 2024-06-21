package com.example.dogs.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class), version = 1)
abstract class DogDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao

    // Using SINGLETON PATTERN
    companion object {
        @Volatile private var instance: DogDatabase? = null
        private val LOCK = Any()

        // if instance isn't null is returned, otherwise is built under a lock. THREAD-SAFE
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }

}