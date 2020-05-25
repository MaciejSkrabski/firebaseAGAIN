package com.example.thistimeletmeunderstandmvvm


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView



class BooksAdapter: RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(){
    val TAG = "BooksAdapter"
    private val bookList:MutableList<BookRow> = mutableListOf()

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int)
            : BooksViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.recycler_view_books, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position:Int) {
        holder.title.text = bookList[holder.adapterPosition].title
        holder.author.text = bookList[holder.adapterPosition].author
        holder.read.text = bookList[holder.adapterPosition].read.toString()

        holder.itemView.setOnClickListener{
            Log.d("CLICK", "RECYCLERVIEW ${bookList[holder.adapterPosition].comment}")
            val nav = Navigation.findNavController(it)
            nav.navigate(R.id.editRecord)
        }


    }

    fun setbooks(newBooks:List<BookRow>){
        bookList.clear()
        bookList.addAll(newBooks)
        notifyDataSetChanged()
    }

    fun addBook(book: BookRow) {
        if(!bookList.contains(book))
            notifyDataSetChanged()

    }




    inner class BooksViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.book_title) as TextView
        val author = view.findViewById(R.id.book_author) as TextView
        val read = view.findViewById(R.id.read) as TextView
    }
}
/*class ItemListener() {
    fun onClick(night: BookRow) =
}*/


