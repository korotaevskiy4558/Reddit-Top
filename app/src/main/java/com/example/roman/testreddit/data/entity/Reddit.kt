package com.example.roman.testreddit.data.entity

data class Result(val reddit: Reddit) {
    val id: String
    val title: String
    val author: String
    val subreddit: String
    val created: Int
    val thumbnail: String
    val ups: Int
    val numComments: Int
    val permalink: String
    init {
         id = reddit.id
         title = reddit.title
         author = reddit.author
         subreddit = reddit.subreddit
         created = reddit.created
         thumbnail = reddit.thumbnail
         ups = reddit.ups
         numComments = reddit.numComments
         permalink = reddit.permalink
    }
}