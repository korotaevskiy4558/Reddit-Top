package com.example.domain.model

class Reddit(  val id: String,
               val title: String,
               val author: String,
               val subreddit: String,
               val created: Int,
               val thumbnail: String,
               val ups: Int,
               val numComments: Int,
               val permalink: String) {

}