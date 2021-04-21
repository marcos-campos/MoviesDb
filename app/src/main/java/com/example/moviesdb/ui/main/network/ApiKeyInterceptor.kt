package com.example.moviesdb.ui.main.network

import com.example.moviesdb.ui.main.repository.RepositoryApi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class ApiKeyInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()
        val originalUrl: HttpUrl = originalRequest.url
        val newUrl: HttpUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", RepositoryApi.chave)
                .build()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()
                .url(newUrl)
        val request: Request = requestBuilder.build()

        return chain.proceed(request)

    }

}