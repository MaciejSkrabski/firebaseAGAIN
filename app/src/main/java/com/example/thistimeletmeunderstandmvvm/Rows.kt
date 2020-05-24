package com.example.thistimeletmeunderstandmvvm

class BookRow ( val title: String,
                val author: String,
                val read: Boolean?=false)

class FilmRow ( val title: String,
                val director: String,
                val watched: Boolean?=false,
                val comment: String)

class GameRow (val title: String,
               val studio: String,
               val played: Boolean?=false)