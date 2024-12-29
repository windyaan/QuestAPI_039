package com.example.myapplication.kelas

import com.example.myapplication.repository.MahasiswaRepository
import com.example.myapplication.repository.NetworkKontakRepository
import com.example.myapplication.service_api.MahasiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val kontakRepository: MahasiswaRepository
}
