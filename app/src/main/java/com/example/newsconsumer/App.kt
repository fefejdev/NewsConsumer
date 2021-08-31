package com.example.newsconsumer

import android.app.Application
import com.example.newsconsumer.di.apiModule
import com.example.newsconsumer.repository.repoModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        startKoin {
            modules(listOf(
                apiModule,
                repoModule
            ))
        }
        super.onCreate()
    }
}