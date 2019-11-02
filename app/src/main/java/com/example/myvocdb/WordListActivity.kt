package com.example.myvocdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    fun GoToMain (view: View){
        //val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        //myToast.show()

        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)


    }




}
