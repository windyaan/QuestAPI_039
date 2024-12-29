package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Mahasiswa
import com.example.myapplication.repository.MahasiswaRepository

sealed class HomeUiState{
    data class Success(
        val mahasiswa: List<Mahasiswa>
    ): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()
}

