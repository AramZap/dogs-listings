package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.databinding.ItemDogBinding
import com.example.dogs.model.DogBreed
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickListener {

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogsList[position]
        holder.view.listener = this
    }

    override fun onDogClicked(v: View) {
        val uuid = v.findViewById<TextView>(R.id.dogId).text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }

    fun updateDogList(newDogsList : List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }
}