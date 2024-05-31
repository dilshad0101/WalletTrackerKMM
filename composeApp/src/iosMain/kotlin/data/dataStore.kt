package data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.app.spendr.data.datastore.createDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun dataStore(get: Any): DataStore<Preferences> = createDataStore(

    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/currencyPreference"
    }
)

actual val platformModule: Module = module {
    single { dataStore(get())}
}
