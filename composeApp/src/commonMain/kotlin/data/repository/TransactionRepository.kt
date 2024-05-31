package data.repository

import androidx.room.Transaction
import data.local.TransactionDao
import kotlinx.coroutines.flow.StateFlow

class TransactionRepository(private val transactionDao: TransactionDao){
    val readAllData: StateFlow<List<Transaction>> = transactionDao.readAllData()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.addTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction){
        transactionDao.deleteTransaction(transaction)
    }

    suspend fun deleteAllTransaction(){
        transactionDao.deleteAllTransaction()
    }
}