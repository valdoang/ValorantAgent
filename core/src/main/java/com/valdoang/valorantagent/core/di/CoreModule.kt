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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("valdoang".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ValorantDatabase::class.java, "Valorant.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "valorant-api.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/qrCJ+v05fGIiGr+ojQXfXCCVi8Qb0o1qPrxOt0Sd/HY=")
            .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) })
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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