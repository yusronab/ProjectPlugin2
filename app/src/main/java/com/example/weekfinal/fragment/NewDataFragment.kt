package com.example.weekfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weekfinal.databinding.FragmentNewDataBinding
import com.example.weekfinal.model.Post
import com.example.weekfinal.response.Communicator
import com.example.weekfinal.response.ListResponse
import com.example.weekfinal.webservice.APIservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewDataFragment : Fragment() {
    private lateinit var binding: FragmentNewDataBinding
    private lateinit var com: Communicator
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentNewDataBinding.inflate(layoutInflater)
        com = activity as Communicator
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnPostData()
    }

    private fun postData(){
        val nama = binding.etFragmentNewData.text.toString()
        val kode = binding.etFragmentNewCode.text.toString().toInt()
        APIservice.APIendPoint().addPost(nama, kode).enqueue(object : Callback<ListResponse<Post>>{
            override fun onResponse(call: Call<ListResponse<Post>>, response: Response<ListResponse<Post>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        Toast.makeText(activity, "New Item has been created", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Post>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun btnPostData(){
        binding.btnFragmentAddData.setOnClickListener {
            postData()
            com.createData(binding.btnFragmentAddData.text.toString())
        }
    }
}