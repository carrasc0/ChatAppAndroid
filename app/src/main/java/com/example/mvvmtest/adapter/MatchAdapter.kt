package com.example.mvvmtest.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmtest.R
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.util.inflate
import kotlinx.android.synthetic.main.design_match.view.*

class MatchAdapter(private var items: List<Match>) : RecyclerView.Adapter<MatchAdapter.MatchVH>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchVH {
        val inflatedView = parent.inflate(R.layout.design_match, false)
        return MatchVH(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MatchVH, position: Int) {
        val itemMatch = items[position]
        holder.bindMatch(itemMatch)
    }

    class MatchVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var match: Match? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bindMatch(match: Match) {
            this.match = match
            itemView.nameTextView.text = match.name
            itemView.bodyTextView.text = match.last_message
            Glide.with(itemView.context)
                    .asBitmap()
                    .load(match.image)
                    .into(itemView.userImageView)
        }

        override fun onClick(v: View?) {
            Toast.makeText(itemView.context, "Pressed: ${v.toString()}", Toast.LENGTH_SHORT).show()
        }

    }
}