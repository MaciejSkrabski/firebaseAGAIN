package com.example.thistimeletmeunderstandmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class BookViewModel():ViewModel(){
    private val _result = MutableLiveData<Exception?>()
    private val dbBooks = FirebaseDatabase.getInstance().getReference("books")

    val result:LiveData<Exception?>
        get() = _result

    fun addBook(book: BookRow){

        book.id = dbBooks.push().key
        dbBooks.child(book.id!!).setValue(book)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _result.value = null
                }else{
                    _result.value  = it.exception
                }
            }
    }


}