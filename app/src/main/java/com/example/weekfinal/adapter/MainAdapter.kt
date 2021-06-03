package com.example.weekfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfinal.databinding.ItemListBinding
import com.example.weekfinal.model.Post

class MainAdapter(private var post: List<Post>, val listener: onAdapterClick): RecyclerView.Adapter<MainAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    interface onAdapterClick{
        fun onUpdate(post: Post)
        fun onClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            tvPlaceHolder.text = post[position].nama
            iconUpdate.setOnClickListener {
                listener.onUpdate(post[position])
            }
            cardView.setOnClickListener {
                listener.onClick(post[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return post.size
    }

}