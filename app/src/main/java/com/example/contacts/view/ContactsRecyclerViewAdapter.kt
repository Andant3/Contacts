package com.example.contacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contacts.R
import com.example.contacts.databinding.CardItemBinding
import com.example.contacts.room.ContactModel

class ContactsRecyclerViewAdapter(
    private val contactList: List<ContactModel>,
    private val clickListener: (ContactModel) -> Unit)
    : RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: CardItemBinding)
        : ViewHolder(binding.root){
            fun bind(contactModel: ContactModel, clickListener: (ContactModel) -> Unit){
                binding.nameTextView.text = contactModel.name
                binding.emailTextView.text = contactModel.email
                binding.listItemLayout.setOnClickListener {
                    clickListener(contactModel)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: CardItemBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.card_item, parent, false)

        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener)
    }
}