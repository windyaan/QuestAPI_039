package com.example.mydatasiswa.repositori

import com.example.mydatasiswa.apiservice.ServiceApiSiswa
import com.example.mydatasiswa.modeldata.DataSiswa

interface RepositoryDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) :retrofit2.Response<Void>
    suspend fun getSatuSiswa(id: Int): DataSiswa
    suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa) :retrofit2.Response<Void>
    suspend fun hapusSatuSiswa(id: Int) :retrofit2.Response<Void>
}

class JaringanDataRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoryDataSiswa{
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa):retrofit2.Response<Void>  = serviceApiSiswa.postSiswa (dataSiswa)
    override suspend fun getSatuSiswa(id: Int): DataSiswa = serviceApiSiswa.getSatuSiswa(id)
    override suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa):retrofit2.Response<Void>  = serviceApiSiswa.editSatuSiswa (id, dataSiswa)
    override suspend fun hapusSatuSiswa(id: Int):retrofit2.Response<Void>  = serviceApiSiswa.hapusSatuSiswa(id)
    }