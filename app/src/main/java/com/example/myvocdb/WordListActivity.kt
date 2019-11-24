package com.example.myvocdb

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myvocdb.Adapter.WordListAdapter
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import kotlinx.android.synthetic.main.activity_word_list.*
import java.util.*

class WordListActivity : AppCompatActivity() {

    var dbHelper = DBHelper(this)

    private val p = Paint()

    private var wlAdapter: WordListAdapter? = null

    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))

    var removedList:ArrayList<Item> = ArrayList()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        var data = dbHelper.readAllWords()
        //listWord = data

        data.reverse()

        wlAdapter = WordListAdapter(this, data)
        recycleView_wordlist.layoutManager = LinearLayoutManager(this)
        recycleView_wordlist.adapter = wlAdapter

        //saveButton?.visibility = View.VISIBLE
        enableSwipe()
    }


    private fun enableSwipe() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                   removedList.add((wlAdapter as WordListAdapter).removeItem(viewHolder))

                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX > 0) {
                            //p.color = Color.parseColor("#388E3C")
                        swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)

                    } else {
                            //p.color = Color.parseColor("#D32F2F")
                        swipeBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                    }

                    swipeBackground.draw(c)

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycleView_wordlist)

    }





    fun GoToMain (view: View){

        for(item in removedList){

            dbHelper.deleteWord(item.word.toString())
        }




        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)


    }




}
