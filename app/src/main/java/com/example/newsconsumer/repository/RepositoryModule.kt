package com.example.newsconsumer.repository

import org.koin.dsl.module

val repoModule = module {
    single { SearchRepository() }
    single { ImageDownloader() }
}