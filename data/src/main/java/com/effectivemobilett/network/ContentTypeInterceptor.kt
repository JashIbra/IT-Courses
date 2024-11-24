package com.effectivemobilett.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val contentType = "application/json; charset=utf-8".toMediaType()
        val newRequest = chain.request().newBuilder()
            .header("Content-Type", contentType.toString())
            .build()
        return chain.proceed(newRequest)
    }
}
