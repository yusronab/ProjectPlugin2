package com.example.weekfinal.model

data class User (
        var id: Int,
        var name: String,
        var username: String,
        var email: String,
        var password: String,
        var updatedAt: String,
        var createdAt: String,
        var token: String
)