package com.example.thistimeletmeunderstandmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class BookViewModel():ViewModel(){
    private val _result = MutableLiveData<Exception?>()

    val result:LiveData<Exception?>
        get() = _result

    fun addBook(book: BookRow){
        val dbBooks = FirebaseDatabase.getInstance().getReference("books")
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