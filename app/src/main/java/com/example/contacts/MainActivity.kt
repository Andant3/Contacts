package com.example.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.repository.ContactRepository
import com.example.contacts.room.ContactDAO
import com.example.contacts.room.ContactModel
import com.example.contacts.room.ContactsDataBase
import com.example.contacts.view.ContactsRecyclerViewAdapter
import com.example.contacts.viewmodel.ContactViewModel
import com.example.contacts.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main)

        val dao = ContactsDataBase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        contactViewModel = ViewModelProvider(this, factory)
            .get(ContactViewModel::class.java)

        binding.contactViewModel = contactViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        contactViewModel.contacts.observe(this, Observer {
            binding.contactsRecyclerView.adapter = ContactsRecyclerViewAdapter(
                it
            ) { selectedItem: ContactModel ->
                listItemClicked(selectedItem)
            }
        })
    }

    private fun listItemClicked(selectedItem: ContactModel) {

        Toast.makeText(
            this,
            "${selectedItem.name} clicked",
            Toast.LENGTH_SHORT
        ).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}