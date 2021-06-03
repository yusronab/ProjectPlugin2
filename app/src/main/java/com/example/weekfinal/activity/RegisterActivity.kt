package com.example.weekfinal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weekfinal.databinding.ActivityRegisterBinding
import com.example.weekfinal.model.User
import com.example.weekfinal.response.SingleResponse
import com.example.weekfinal.webservice.APIservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnBack()
        btnRegister()
    }

    private fun btnBack(){
        binding.iconBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun register(){
        val name = binding.etRegName.text.toString()
        val username = binding.etRegUsername.text.toString()
        val email = binding.etRegEmail.text.toString()
        val password = binding.etRegPassword.text.toString()
        APIservice.APIendPoint().register(name, username, email, password).enqueue(object: Callback<SingleResponse<User>>{
            override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Toast.makeText(applicationContext, body.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun btnRegister(){
        binding.btnToRegister.setOnClickListener {
            register()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}