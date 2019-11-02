package com.example.myvocdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvocdb.Adapter.WordListAdapter
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity() {

    var dbHelper = DBHelper(this)

    var  listWord = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        var data = dbHelper.readAllWords()

        recycleView_wordlist.layoutManager = LinearLayoutManager(this)
        recycleView_wordlist.adapter = WordListAdapter(this, data)

    }
    
}
