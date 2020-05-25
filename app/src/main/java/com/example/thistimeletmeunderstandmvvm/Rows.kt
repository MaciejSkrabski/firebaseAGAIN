package com.example.thistimeletmeunderstandmvvm

import com.google.firebase.database.Exclude

data class BookRow (
    @get:Exclude
    var id: String? = null,
    var title: String? = null,
    var author: String? = null,
    var read: Boolean? = null,
    var comment: String? = null )
{
    override fun equals(other: Any?): Boolean {
        return if (other is BookRow){
            other.id == id
        } else false

    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (read?.hashCode() ?: 0)
        result = 31 * result + (comment?.hashCode() ?: 0)
        return result
    }
}

data class FilmRow (
    @get:Exclude
    var String: Int? = null,
    var title: String? = null,
    var director: String? = null,
    var watched: Boolean? = null
    )

data class GameRow (
    @get:Exclude
    var id: String? = null,
    var title: String? = null,
    var studio: String? = null,
    var played: Boolean? = null)