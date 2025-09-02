package com.example.mydatasiswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa{
    data class Success(val siswa: List<DataSiswa> = listOf()) :StatusUiSiswa
    object Error : StatusUiSiswa
    object Lodaing : StatusUiSiswa
}

class HomeViewModel (private val repositoryDataSiswa: RepositoryDataSiswa): ViewModel(){
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Lodaing)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa(){
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Lodaing
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getDataSiswa())
            }catch (e:IOException){
                StatusUiSiswa.Error
            }
            catch (e: HttpException){
                StatusUiSiswa.Error
            }
        }
    }
}