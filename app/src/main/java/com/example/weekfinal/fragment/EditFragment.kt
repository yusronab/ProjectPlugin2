package com.example.weekfinal.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weekfinal.databinding.FragmentEditBinding
import com.example.weekfinal.model.Post
import com.example.weekfinal.response.Communicator
import com.example.weekfinal.response.SingleResponse
import com.example.weekfinal.webservice.APIservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var com: Communicator
    var nama: String? = ""
    var kode: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        com = activity as Communicator
        setDataOnEdit()
        btnUpdate()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnBack()
    }

    private fun setDataOnEdit(){
        val nama = arguments!!.getString("nama")
        val kode = arguments!!.getInt("kode")
        println("Nama barang $nama")
        binding.etFragmentEditData.setText(nama)
        binding.etFragmentEditCode.setText(kode.toString())
    }

    private fun updateData(){
        val id = arguments!!.getInt("id",0)
        val nama = binding.etFragmentEditData.text.toString()
        val kode = binding.etFragmentEditCode.text.toString()
        APIservice.APIendPoint().updatePost(id, nama, kode).enqueue(object :
            Callback<SingleResponse<Post>> {
            override fun onResponse(
                call: Call<SingleResponse<Post>>,
                response: Response<SingleResponse<Post>>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "Edit Data Success", Toast.LENGTH_SHORT).show()
                    Log.d("Update data", "onResponse:Success ")
                }
            }

            override fun onFailure(call: Call<SingleResponse<Post>>, t: Throwable) {
                println(t.message)
                Toast.makeText(activity, "Update data gagal", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun btnUpdate(){
        binding.btnFragmentSaveData.setOnClickListener {
            updateData()
        }
    }

    private fun btnBack(){
        binding.iconBack.setOnClickListener {
            com.createData(binding.iconBack.toString())
        }
    }
}