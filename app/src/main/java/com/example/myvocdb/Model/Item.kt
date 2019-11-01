package com.example.myvocdb.Model

class Item {
    var word: String? = null
    var meaning: String? = null


    constructor(word:String, meaning:String) {
        this.word = word
        this.meaning = meaning
    }
    constructor(word: String) {
        this.word = word
    }
}