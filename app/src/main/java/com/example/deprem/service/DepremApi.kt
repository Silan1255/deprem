package com.example.deprem.service

import com.example.deprem.model.depremListRespose
import io.reactivex.Single
import retrofit2.http.GET

interface DepremApi {

    companion object {
        private const val Deprem = "live.php?limit=100"
    }

    @GET(Deprem)
    fun getDeprem(): Single<depremListRespose>
}