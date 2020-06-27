package com.example.takeawayrestaraunts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.takeawayrestaraunts.db.DBConstants.DB_NAME
import com.example.takeawayrestaraunts.db.daos.RestaurantsDao
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 1,
    exportSchema = false
)

abstract class TakeAwayDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): TakeAwayDb {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, TakeAwayDb::class.java)
            } else {
                databaseBuilder(context, TakeAwayDb::class.java, DB_NAME)
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun restaurantsDao(): RestaurantsDao
}
