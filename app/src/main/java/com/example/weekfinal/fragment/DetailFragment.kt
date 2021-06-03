package com.example.weekfinal.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weekfinal.databinding.FragmentDetailBinding
import com.example.weekfinal.databinding.FragmentHomeBinding
import com.example.weekfinal.model.Post
import com.example.weekfinal.response.Communicator
import com.example.weekfinal.response.ListResponse
import com.example.weekfinal.webservice.APIservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var com: Communicator
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        com = activity as Communicator
        btnDelete()
        btnBack()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getPostById()
    }

    private fun showPostData(post: List<Post>){
        binding.tvItemName.text = post[0].nama
        binding.tvItemCode.text = post[0].kode.toString()
    }

    private fun getPostById(){
        APIservice.APIendPoint().getPostById(arguments!!.getInt("id",0)).enqueue(object : Callback<ListResponse<Post>>{
            override fun onResponse(call: Call<ListResponse<Post>>, response: Response<ListResponse<Post>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showPostData(body.data)
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Post>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun deletePost(){
        APIservice.APIendPoint().deletePost(arguments!!.getInt("id",0)).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun btnDelete(){
        binding.btnFragmentDelData.setOnClickListener {
            deletePost()
            com.createData(binding.btnFragmentDelData.text.toString())

        }
    }

    private fun btnBack(){
        binding.iconBack.setOnClickListener {
            com.createData(binding.iconBack.toString())
        }
    }
}