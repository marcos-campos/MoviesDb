package com.example.moviesdb.network

import com.example.moviesdb.repository.RepositoryApi
import okhttp3.*
import java.io.IOException

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