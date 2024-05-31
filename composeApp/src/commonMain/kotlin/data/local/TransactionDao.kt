package data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.StateFlow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transaction: Transaction)

    @Query("SELECT * FROM app_data ORDER By id ASC")
    fun readAllData(): StateFlow<List<Transaction>>

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM app_data")
    suspend fun deleteAllTransaction()
}