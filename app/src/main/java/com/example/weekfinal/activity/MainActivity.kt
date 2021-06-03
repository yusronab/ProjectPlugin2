package com.example.weekfinal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weekfinal.databinding.ActivityMainBinding
import com.example.weekfinal.webservice.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnSignIn()
        btnSignUp()
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }

    private fun isLogin(){
        val token = Constant.getToken(this)
        if (!token.equals("Undef")){
            startActivity(Intent(this, FragmentActivity::class.java)).also { finish() }
        }
    }

    private fun btnSignIn(){
        binding.btnMainSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun btnSignUp(){
        binding.btnMainSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}