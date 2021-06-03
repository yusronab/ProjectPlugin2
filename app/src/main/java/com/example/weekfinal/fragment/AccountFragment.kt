package com.example.weekfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weekfinal.databinding.FragmentAccountBinding
import com.example.weekfinal.webservice.Constant

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val name = Constant.getName(activity!!)
        binding.tvName.text = name

        val username = Constant.getUsername(activity!!)
        binding.tvUsername.text = username

        val email = Constant.getEmail(activity!!)
        binding.tvEmail.text = email
    }
}