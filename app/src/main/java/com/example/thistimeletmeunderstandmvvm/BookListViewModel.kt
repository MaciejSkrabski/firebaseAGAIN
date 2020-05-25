package com.example.thistimeletmeunderstandmvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import java.lang.Exception

class BookListViewModel : ViewModel() {
    val TAG = "BookListViewModel"
    private val _result = MutableLiveData<Exception?>()
    private val dbBooks = FirebaseDatabase.getInstance().getReference("books")
    private val _bookList: MutableLiveData<List<BookRow>> = MutableLiveData()
        val bookList: LiveData<List<BookRow>>
        get() = _bookList

    private val _singleBook = MutableLiveData<BookRow>()
    val book: LiveData<BookRow>
        get() = _singleBook

    private val childEventListener = object : ChildEventListener{
        override fun onCancelled(error: DatabaseError) {
            Log.e(TAG, "NOT GOOD, DATABASE ERROR IN CHILDEVENTLISTENER")
        }

        override fun onChildMoved(snapshot: DataSnapshot, p1: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val book = snapshot.getValue(BookRow::class.java)
            book?.id = snapshot.key
            _singleBook.value = book
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }
    }

    fun getRealTimeUpdates(){
        dbBooks.addChildEventListener(childEventListener)
    }

    fun fetchBooks() {
        dbBooks.addListenerForSingleValueEvent(object: ValueEventListener {

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

    override fun onCleared() {
        super.onCleared()
        dbBooks.removeEventListener(childEventListener)
    }
}
