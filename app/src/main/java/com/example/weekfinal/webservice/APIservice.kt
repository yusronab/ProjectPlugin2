package com.example.weekfinal.webservice

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIservice {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

        private fun getClient(): Retrofit{
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun APIendPoint(): APIendPoint = getClient().create(APIendPoint::class.java)
    }
}

class Constant{
    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun setToken(context: Context, token: String){
            val sharedPreferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("Token", token)
                apply()
            }
        }

        fun getToken(context: Context): String{
            val sharedPref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val token = sharedPref.getString("Token", "Undef")
            return token!!
        }

        fun clearToken(context: Context){
            val sharedPref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
        }

        fun setName(context: Context, name: String){
            val sharedPref = context.getSharedPreferences("Name", Context.MODE_PRIVATE)
            sharedPref.edit().apply {
                putString("Name", name)
                apply()
            }
        }

        fun getName(context: Context): String{
            val sharePref = context.getSharedPreferences("Name", Context.MODE_PRIVATE)
            val name = sharePref.getString("Name", "Undef")
            return name!!
        }

        fun setUsername(context: Context, username: String){
            val sharedPref = context.getSharedPreferences("Username", Context.MODE_PRIVATE)
            sharedPref.edit().apply {
                putString("Username", username)
                apply()
            }
        }

        fun getUsername(context: Context): String{
            val sharePref = context.getSharedPreferences("Username", Context.MODE_PRIVATE)
            val username = sharePref.getString("Username", "Undef")
            return username!!
        }

        fun setEmail(context: Context, email: String){
            val sharedPref = context.getSharedPreferences("Email", Context.MODE_PRIVATE)
            sharedPref.edit().apply {
                putString("Email", email)
                apply()
            }
        }

        fun getEmail(context: Context): String{
            val sharePref = context.getSharedPreferences("Email", Context.MODE_PRIVATE)
            val email = sharePref.getString("Email", "Undef")
            return email!!
        }
    }
}