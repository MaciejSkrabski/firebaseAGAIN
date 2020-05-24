package com.example.thistimeletmeunderstandmvvm

import com.google.firebase.database.Exclude

data class BookRow (
    @get:Exclude
    var id: String? = null,
    var title: String? = null,
    var author: String? = null,
    var read: Boolean? = null,
    var comment: String? = null)

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