package com.example.reese.dungeondorks

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button

class SpellbookRecyclerAdapter(private val myDataset: MutableList<Spell>) :
    RecyclerView.Adapter<SpellbookRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    fun setData(spells : MutableList<Spell>) {
        myDataset.clear()
        spells.forEach {
            myDataset.add(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SpellbookRecyclerAdapter.MyViewHolder {
        //val myIndex = parent.indexOfChild(this)
        val button = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_spell_book_item, parent, false) as Button
        val vh = MyViewHolder(button)
        button.setOnClickListener{
            val i = vh.adapterPosition
            val spell = myDataset[i]
            val intent = Intent(parent.context, SpellDetailsActivity::class.java)
            intent.putExtra("SPELL_URL", spell.url)
            parent.context.startActivity(intent)
        }
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.button.text = myDataset[position].name
    }

    override fun getItemCount() = myDataset.size
}
