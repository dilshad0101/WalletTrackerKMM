package data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "app_data")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val amount : Int,
    val isSavings: Boolean,
    val date : LocalDate
)


