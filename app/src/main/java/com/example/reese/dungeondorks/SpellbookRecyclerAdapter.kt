package com.example.reese.dungeondorks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class SpellbookRecyclerAdapter(private val myDataset: MutableList<Spell>) :
    RecyclerView.Adapter<SpellbookRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    fun setData(spells : MutableList<Spell>) {
        myDataset.clear()
        spells.forEach {
            myDataset.add(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SpellbookRecyclerAdapter.MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_spell_book_item, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position].name
    }

    override fun getItemCount() = myDataset.size
}
