package com.abrahamrh.harrypotter.views.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrahamrh.harrypotter.databinding.CharacterElementBinding
import com.abrahamrh.harrypotter.model.Character
import com.bumptech.glide.Glide

class CharactersAdapter(
    private var context: Context,
    private var characters: ArrayList<Character>,
) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(view: CharacterElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivThumbnail = view.ivThumbnail
        val tvName = view.tvName
        val tvActor = view.tvActor
        val tvCasa = view.tvCasa
        val tvGender = view.tvGender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = characters[position].name
        holder.tvCasa.text = characters[position].house
        holder.tvGender.text = characters[position].gender
        holder.tvActor.text = characters[position].actor
        Glide.with(context)
            .load(characters[position].image)
            .into(holder.ivThumbnail)
        holder.itemView.setOnClickListener {
        }
    }
}