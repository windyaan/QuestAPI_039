package com.example.mydatasiswa.repositori

import android.app.Application
import com.example.mydatasiswa.apiservice.ServiceApiSiswa
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContainerApp {
    val repositoryDataSiswa : RepositoryDataSiswa
}

class DefaultContainerApp : ContainerApp{
    private val baseurl = "http://10.0.22/umyTI/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType())
        )
        //.client(klient)
        .build()

    private val retrofitService : ServiceApiSiswa by lazy {
        retrofit.create(ServiceApiSiswa::class.java)
    }

    override val repositoryDataSiswa: RepositoryDataSiswa by lazy {
        JaringanDataRepositoryDataSiswa(retrofitService)
    }
}

class AplikasiDataSiswa: Application(){
    lateinit var container : ContainerApp
    override fun onCreate() {
        super.onCreate()
        this.container = DefaultContainerApp()
    }
}