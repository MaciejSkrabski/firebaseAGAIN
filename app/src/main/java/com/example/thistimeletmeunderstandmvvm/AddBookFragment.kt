package com.example.thistimeletmeunderstandmvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_book_dialog.*
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AddBookFragment : Fragment() {
    private val TAG = "BookFragment"
    // dodawanie działa dobrze

    private val model: BookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.result.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it == null){
                requireContext().toast("Book added")
            }else{
                requireContext().toast(it!!.message!!)
            }
        })


        submit_book.setOnClickListener {
            Log.d(TAG, "${FirebaseAuth.getInstance().currentUser}")
            Log.d(TAG, "${FirebaseDatabase.getInstance().getReference("message")}")
            val t = add_title.text.toString().trim()
            if(t.isEmpty()){
                add_title.error = getString(R.string.field_required)
                return@setOnClickListener
            }

            val n = add_author.text.toString().trim()
            if(n.isEmpty()){
                add_author.error = getString(R.string.field_required)
                return@setOnClickListener
            }

            val c = add_comment.text.toString().trim()

            val isRead = switch_read.isChecked

            val book = BookRow()
            book.title = t
            book.author = n
            book.comment = c
            book.read = isRead
            model.addBook(book)

            add_title.setText("")
            add_author.setText("")
            add_comment.setText("")
            switch_read.isChecked = false

        }
    }

}
