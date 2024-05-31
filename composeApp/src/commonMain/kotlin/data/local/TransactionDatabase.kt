package data.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import domain.Converters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(Converters::class)
abstract class TransactionDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object{
        @Volatile
        private var INSTANCE : TransactionDatabase? = null
        fun getDatabase(): TransactionDatabase{
            return builder
                .fallbackToDestructiveMigrationOnDowngrade(true)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }



}