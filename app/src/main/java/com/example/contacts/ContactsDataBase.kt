package com.example.contacts

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [ContactModel::class], version = 1)
abstract class ContactsDataBase : RoomDatabase() {

    abstract val contactDAO: ContactDAO

    companion object{
        @Volatile
        private var INSTANCE: ContactsDataBase ?= null

        fun getInstance(context: Context): ContactsDataBase{

            synchronized(this){

                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactsDataBase::class.java,
                        "contacts_database"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}