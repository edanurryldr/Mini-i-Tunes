package com.edanuryildirim.itunes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.edanuryildirim.itunes.R
import com.edanuryildirim.itunes.model.Articles
import com.edanuryildirim.itunes.model.NewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val articleList: ArrayList<Articles>, private val listener: Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(),
    Filterable {

    var articleListFiltered = articleList

    interface Listener {
        fun onItemClick(article: Articles)
        fun handleRespone(newsModel: NewsModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article:Articles, position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(article)
            }
            itemView.text_name.text = article.artistName
            itemView.text_name1.text = article.collectionName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        //hangi item ne verisi gösterecek onu yazıyoruz
        holder.bind(articleListFiltered[position],position,listener)

        //Image
        val url2 = "${articleList[position].artworkUrl100}"
        Picasso.get().load(url2).into(holder.itemView.imageNews)
    }

    override fun getItemCount(): Int {
        return articleListFiltered.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) articleListFiltered = articleList else {
                    val filteredList = ArrayList<Articles>()
                    articleList
                        .filter {
                            (it.artistName.contains(constraint!!)) or
                                    (it.collectionName.contains(constraint))

                        }
                        .forEach { filteredList.add(it) }
                    articleListFiltered = filteredList

                }
                return FilterResults().apply { values = articleListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                articleListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Articles>
                notifyDataSetChanged()
            }
        }
    }
}