package com.example.weekfinal.response

interface Communicator {
    fun passData(id: Int)
    fun passId(id: Int)
    fun passNama(nama: String)
    fun passKode(kode: Int)
    fun createData(name: String)
}