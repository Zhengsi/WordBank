package com.example.myvocdb.Model

class Item {
    var word: String? = null
    var meaning: String? = null
    var audioNotePath: String? = null


    constructor(word:String, meaning:String, audioNotePath: String) {
        this.word = word
        this.meaning = meaning
        this.audioNotePath = audioNotePath
    }
    constructor(word: String) {
        this.word = word
    }
}