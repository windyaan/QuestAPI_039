package com.example.myapplication

import android.app.Application
import com.example.myapplication.kelas.AppContainer
import com.example.myapplication.kelas.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}