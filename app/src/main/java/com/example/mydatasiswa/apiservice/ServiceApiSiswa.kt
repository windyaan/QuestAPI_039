package com.example.mydatasiswa.apiservice

import retrofit2.http.GET

interface ServiceApiSiswa {
    @GET(bacaTeman.php)
    suspend fun getSiswa(): List<Siswa>
}