package com.example.contacts.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: ContactModel): Long

    @Update
    suspend fun updateContact(contact: ContactModel)

    @Delete
    suspend fun deleteContact(contactModel: ContactModel)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    @Query("Select * FROM contacts_table")
    fun getAllContactsFromDB(): LiveData<List<ContactModel>>
}