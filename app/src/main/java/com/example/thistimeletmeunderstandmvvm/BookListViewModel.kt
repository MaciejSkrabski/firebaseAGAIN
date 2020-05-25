package com.example.thistimeletmeunderstandmvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class BookListViewModel : ViewModel() {

    val TAG = "BookListViewModel"

    private val dbBooks = FirebaseDatabase.getInstance()
    private val _bookList: MutableLiveData<List<BookRow>> = MutableLiveData()
        val bookList: LiveData<List<BookRow>>
        get() = _bookList


    fun fetchBooks() {
        var fetchedBooks = dbBooks.getReference("books")
        Log.d(TAG, "fetching inside booklistviewmodel")
        fetchedBooks.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {Log.d(TAG, "onCancelled")}

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange")

                if (snapshot.exists()) {
                    val books = mutableListOf<BookRow>()
                    for (bookSnapshot in snapshot.children) {
                        val book = bookSnapshot.getValue(BookRow::class.java)
                        book?.id = bookSnapshot.key
                        book?.let { books.add(it) }
                    }
                    _bookList.value = books
                } else { Log.d(TAG, "snapshot existn't") }
            }
        }
        )
    }
}
