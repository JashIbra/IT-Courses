package com.effectivemobilett.di

import com.effectivemobilett.network.ContentTypeInterceptor
import com.example.data.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.java.KoinJavaComponent.get
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit(): Retrofit = get(Retrofit::class.java)

fun netWorkModule() = module {

    factoryOf(::ContentTypeInterceptor)

    factory {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory<GsonConverterFactory> {
        GsonConverterFactory.create(get(qualifier = qualifier<GsonConverterFactory>()))
    }

    single<Gson>(qualifier = qualifier<GsonConverterFactory>()) { GsonBuilder().create() }

    single {
        val loggingInterceptor: HttpLoggingInterceptor = get()
        val contentTypeInterceptor: ContentTypeInterceptor = get()
        OkHttpClient.Builder()
            .addInterceptor(contentTypeInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(get(GsonConverterFactory::class))
            .client(get(OkHttpClient::class))
            .baseUrl(BuildConfig.BASE_API_URL)
            .build()
    }
}
