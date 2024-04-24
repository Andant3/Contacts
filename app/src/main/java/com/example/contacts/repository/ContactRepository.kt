package com.example.contacts.repository

import com.example.contacts.room.ContactDAO
import com.example.contacts.room.ContactModel

class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsFromDB()

    suspend fun insert(contact: ContactModel): Long{
        return contactDAO.insertContact(contact)
    }

    suspend fun update(contact: ContactModel){
        return contactDAO.updateContact(contact)
    }

    suspend fun delete(contact: ContactModel){
        return contactDAO.deleteContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAllContacts()
    }
}