package com.example.thistimeletmeunderstandmvvm
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_books.*

class BooksFragment : Fragment() {
    private val model: BookListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        fab.setOnClickListener { navController.navigate(R.id.action_booksFragment3_to_addBookFragment2) }
        Log.d("viewCreated", "viewCreated")
        model.fetchBooks()

        Log.d("viewCreated", "afterFetch, ${model.fetchBooks()}")
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bookListAdapter = BooksAdapter()

        recyclerViewBooks.apply {
            adapter = bookListAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        recyclerViewBooks.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))

        //model.fetchBooks()

        model.bookList.observe(viewLifecycleOwner, Observer{
            Log.d("booksFragment","observer")
            bookListAdapter.setbooks(it)
            model.fetchBooks()


        })
    }
}
