package net.simplifiedcoding.spendtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.simplifiedcoding.spendtracker.data.Spend

@Dao
interface SpendDao {

    @Insert
    suspend fun addSpend(spend: Spend)

    @Query("SELECT * FROM spends ORDER BY date DESC LIMIT 20")
    suspend fun getLast20Spends(): List<Spend>

}