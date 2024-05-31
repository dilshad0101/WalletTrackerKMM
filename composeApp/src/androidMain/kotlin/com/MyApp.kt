package com

import android.app.Application
import android.content.Context
import com.app.spendr.data.datastore.initKoinAndroid
import data.KoinInitializer
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
        initKoinAndroid(
            listOf(
                module {
                    single<Context> { this@MyApp }
                }
            )
        )
}
}