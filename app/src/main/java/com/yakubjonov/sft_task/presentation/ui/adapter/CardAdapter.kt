package com.yakubjonov.sft_task.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yakubjonov.domain.model.Card
import com.yakubjonov.sft_task.databinding.ItemCardBinding

class CardAdapter(
    private val listener: OnCardClickListener
) : ListAdapter<Card, CardAdapter.CVH>(DiffUtilCallBack) {


    inner class CVH(private val itemBinding:ItemCardBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun onBind(card: Card){
            itemBinding.apply {
                binTv.text = card.bin.toString()
                root.setOnClickListener {
                    listener.onCardClicked(card)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CVH {
        return CVH(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CVH, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnCardClickListener{
        fun onCardClicked(card: Card)
    }

    object DiffUtilCallBack:DiffUtil.ItemCallback<Card>(){
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.bin == newItem.bin
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.bin == newItem.bin
        }

    }
}