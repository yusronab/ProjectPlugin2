package com.example.weekfinal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weekfinal.databinding.ActivityLoginBinding
import com.example.weekfinal.model.User
import com.example.weekfinal.response.SingleResponse
import com.example.weekfinal.webservice.APIservice
import com.example.weekfinal.webservice.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnBack()
        btnLogin()
    }

    private fun btnBack(){
        binding.iconBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun btnLogin(){
        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin(){
        val username = binding.etLoginUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()
        APIservice.APIendPoint().login(username, password).enqueue(object: Callback<SingleResponse<User>>{
            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Constant.setToken(this@LoginActivity, body.data.token)
                        Constant.setName(this@LoginActivity, body.data.name)
                        Constant.setUsername(this@LoginActivity, body.data.username)
                        Constant.setEmail(this@LoginActivity, body.data.email)
                        Toast.makeText(applicationContext, "Welcome Back ${body.data.username}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, FragmentActivity::class.java))
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
            }
        })
    }
}