package com.example.myvocdb.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import com.example.myvocdb.R
import com.example.myvocdb.WordListActivity
import com.example.myvocdb.WordPageActivity
import kotlinx.android.synthetic.main.row_item.view.*


class WordListAdapter(val mContext: Context, val items: ArrayList<Item>): RecyclerView.Adapter<CustomViewHolder>(){


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // create a view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.row_item, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.textView_word.text = items.get(position).word
        holder.itemView.textView_mean.text = items.get(position).meaning
        holder.itemView.textView_path.text = items.get(position).audioNotePath

        holder.itemView.setOnClickListener {
            //Toast.makeText(mContext, "clicked", Toast.LENGTH_LONG).show()
            //Toast.makeText(mContext, items.get(position).word, Toast.LENGTH_LONG).show()

            val audioIntent = Intent(mContext, WordPageActivity::class.java)

            audioIntent.putExtra("FILE_PATH", items.get(position).audioNotePath)
            //Toast.makeText(applicationContext, pathName, Toast.LENGTH_LONG).show()
            audioIntent.putExtra("NEW_WORD", items.get(position).word)
            audioIntent.putExtra("NEW_MEAN", items.get(position).meaning)

            mContext.startActivity(audioIntent)
        }

    }
}


class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){




}
