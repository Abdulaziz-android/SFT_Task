package com.yakubjonov.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakubjonov.data.local.entity.CardEntity

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardEntity: CardEntity)

    @Query("select * from cardentity where bin = :bin")
    suspend fun getCardByBin(bin:Int):CardEntity?

    @Query("select * from cardentity")
    suspend fun getAllCards():List<CardEntity>



}