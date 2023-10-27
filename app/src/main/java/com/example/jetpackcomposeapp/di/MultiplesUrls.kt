package com.example.jetpackcomposeapp.di

import com.example.jetpackcomposeapp.BuildConfig
import com.example.jetpackcomposeapp.network.RecipeService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class MultiplesUrls {
    private var mGsonConverter: GsonConverterFactory? = null



    @Provides
    fun provideBaseUrl() = "https://food2fork.ca/api/" + BuildConfig.ENDPOINT




    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .readTimeout(1, TimeUnit.MINUTES)    // read timeout
            .writeTimeout(1, TimeUnit.MINUTES)   // write timeout
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .readTimeout(1, TimeUnit.MINUTES)    // read timeout
            .writeTimeout(1, TimeUnit.MINUTES)   // write timeout
            .build()
    }





    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverter)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


/*

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RecipeService = retrofit.create(RecipeService::class.java)
*/


/*

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImp): ApiHelper = apiHelper


*/

    private val gsonConverter: GsonConverterFactory
        get() {
            if (mGsonConverter == null) {
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create()
                    )
            }
            return mGsonConverter!!
        }
}
