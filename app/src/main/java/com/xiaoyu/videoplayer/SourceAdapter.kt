package com.xiaoyu.videoplayer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiaoyu.videoplayer.viewmodel.SourceModelItem
import kotlinx.android.synthetic.main.item_source.view.*

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.VH>() {
    private val data = mutableListOf<SourceModelItem>()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SourceModelItem) {
            itemView.sourceName.text = item.name
//            itemView.sourceName.setOnClickListener{
//                val intent = Intent(itemView.context, PlayActivity::class.java)
//                intent.putExtra("uri", "model.uri")
//                intent.putExtra("name", "model.name")
//                itemView.context.startActivity(intent)
//            }
        }

    }

    fun setData(data: List<SourceModelItem>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false)
        val vh = VH(itemView)
        itemView.sourceName.setOnClickListener {
            val model = data[vh.adapterPosition]
            val intent = Intent(vh.itemView.context, PlayActivity::class.java)
            intent.putExtra("uri", model.uri)
            intent.putExtra("name", model.name)
            itemView.context.startActivity(intent)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

}
