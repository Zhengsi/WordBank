package com.example.myvocdb.Model

class Item {
    var word: String? = null
    var meaning: String? = null
    var audioNotePath: String? = null
    //var countReview: Integer? = null


    constructor(word:String, meaning:String, audioNotePath: String) {
        this.word = word
        this.meaning = meaning
        this.audioNotePath = audioNotePath
       // this.countReview = countReview
    }
    constructor(word: String) {
        this.word = word
    }
}