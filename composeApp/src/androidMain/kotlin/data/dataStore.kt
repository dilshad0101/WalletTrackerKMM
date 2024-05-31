package data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.app.spendr.data.datastore.createDataStore

internal const val dataStoreFileName = "meetings.preferences_pb"

// in src/androidMain/kotlin
fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )
