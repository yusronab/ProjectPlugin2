package com.example.weekfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weekfinal.adapter.MainAdapter
import com.example.weekfinal.databinding.FragmentHomeBinding
import com.example.weekfinal.model.Post
import com.example.weekfinal.response.Communicator
import com.example.weekfinal.response.ListResponse
import com.example.weekfinal.webservice.APIservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var com: Communicator
    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        com = activity as Communicator
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getAllPost()
    }

    fun getAllPost(){
        APIservice.APIendPoint().getAllPost().enqueue(object : Callback<ListResponse<Post>>{
            override fun onResponse(call: Call<ListResponse<Post>>, response: Response<ListResponse<Post>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showToRecycler(body.data)
                        Toast.makeText(activity, "Get all data is Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Post>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    fun showToRecycler(post: List<Post>){
        mainAdapter = MainAdapter(post, object : MainAdapter.onAdapterClick{
            override fun onUpdate(post: Post) {
                com.passId(post.id)
                com.passNama(post.nama)
                com.passKode(post.kode)
            }

            override fun onClick(post: Post) {
                com.passData(post.id)
            }
        })
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}