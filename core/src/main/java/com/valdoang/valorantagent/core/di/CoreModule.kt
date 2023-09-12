package com.valdoang.valorantagent.core.di

import androidx.room.Room
import com.valdoang.valorantagent.core.BuildConfig
import com.valdoang.valorantagent.core.data.ValorantRepository
import com.valdoang.valorantagent.core.data.source.local.LocalDataSource
import com.valdoang.valorantagent.core.data.source.local.room.ValorantDatabase
import com.valdoang.valorantagent.core.data.source.remote.RemoteDataSource
import com.valdoang.valorantagent.core.data.source.remote.network.ApiService
import com.valdoang.valorantagent.core.domain.repository.IValorantRepository
import com.valdoang.valorantagent.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ValorantDatabase>().valorantDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ValorantDatabase::class.java, "Valorant.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) })
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IValorantRepository> {
        ValorantRepository(
            get(),
            get(),
            get()
        )
    }
}