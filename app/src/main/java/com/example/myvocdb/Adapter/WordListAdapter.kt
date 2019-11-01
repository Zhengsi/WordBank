package com.example.myvocdb.Adapter

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myvocdb.DBHelper.DBHelper
import com.example.myvocdb.Model.Item
import com.example.myvocdb.R
import com.example.myvocdb.WordListActivity
import kotlinx.android.synthetic.main.row_item.view.*


class WordListAdapter(mContext: Context, val items: ArrayList<Item>): RecyclerView.Adapter<CustomViewHolder>(){


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
    }


}


class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

}
