package com.abrahamrh.harrypotter.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrahamrh.harrypotter.R
import com.abrahamrh.harrypotter.databinding.CharacterElementBinding
import com.abrahamrh.harrypotter.model.Character
import com.bumptech.glide.Glide

class CharactersAdapter(
    private var context: Context,
    private var characters: ArrayList<Character>,
    private var clickedListener: (Character) -> Unit
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
        val character = characters[position]
        holder.tvName.text = character.name
        holder.tvCasa.text = if(character.house == "") {
            context.getString(R.string.notFound)
        } else {
            character.house
        }
        holder.tvGender.text = if(character.gender == "") {
            context.getString(R.string.notFound)
        } else {
            character.gender
        }
        holder.tvActor.text = if(character.actor == "") {
            context.getString(R.string.notFound)
        } else {
            character.actor
        }
        if(character.image == ""){
            Glide.with(context)
                .load(R.drawable.character_icon)
                .into(holder.ivThumbnail)
        } else {
            Glide.with(context)
                .load(characters[position].image)
                .into(holder.ivThumbnail)
        }
        holder.itemView.setOnClickListener {
            clickedListener(characters[position])
        }
    }
}