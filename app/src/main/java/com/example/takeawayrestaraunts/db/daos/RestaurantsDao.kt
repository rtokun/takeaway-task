package com.example.takeawayrestaraunts.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.takeawayrestaraunts.db.DBConstants.RESTAURANTS_TABLE
import com.example.takeawayrestaraunts.networking.models.restaurant.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM $RESTAURANTS_TABLE")
    fun getAllRestaurants(): Flow<List<Restaurant>>

    @Query("SELECT COUNT(*) FROM $RESTAURANTS_TABLE")
    suspend fun getRestaurantsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestaurants(restaurants: List<Restaurant>)

    @Query("SELECT * from $RESTAURANTS_TABLE WHERE id=:restaurantId")
    suspend fun getRestaurantById(restaurantId: Int): Restaurant?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(restaurant: Restaurant)
}
