package com.abrahamlay.newsearchgithubuser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.newsearchgithubuser.R
import com.abrahamlay.newsearchgithubuser.databinding.ItemSearchResultBinding
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Abraham Lay on 2019-11-22.
 */
class MainAdapter :
    ListAdapter<SearchResultEntity.ItemsItem, MainAdapter.DataHolder>(UsersDiffCallback()) {


    private val clickSubject = PublishSubject.create<SearchResultEntity.ItemsItem>()
    val albumItemClickEvent: Observable<SearchResultEntity.ItemsItem> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val bind: ItemSearchResultBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result, parent, false
        ) as ItemSearchResultBinding

        return DataHolder(bind)
    }


    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val searchItem = getItem(position)
        searchItem?.let { holder.bind(searchItem) }
    }

    inner class DataHolder(private var itemSearchBinding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder (itemSearchBinding.root), View.OnClickListener {

        fun bind(searchItem: SearchResultEntity.ItemsItem) {
            itemSearchBinding.searchItem = searchItem
            itemSearchBinding.root.setOnClickListener(this)
            itemSearchBinding.executePendingBindings()
        }

        override fun onClick(view: View) {
            val item = getItem((adapterPosition))
            item?.let {
                val product: SearchResultEntity.ItemsItem = item
                clickSubject.onNext(product)
            }
        }
    }

    class UsersDiffCallback : DiffUtil.ItemCallback<SearchResultEntity.ItemsItem>() {

        override fun areItemsTheSame(
            oldItem: SearchResultEntity.ItemsItem,
            newItem: SearchResultEntity.ItemsItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SearchResultEntity.ItemsItem,
            newItem: SearchResultEntity.ItemsItem
        ): Boolean =
            oldItem == newItem

        override fun getChangePayload(
            oldItem: SearchResultEntity.ItemsItem,
            newItem: SearchResultEntity.ItemsItem
        ): Any? {
            // Return particular field for changed item.
            return super.getChangePayload(oldItem, newItem)
        }
    }
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if(!url.isNullOrEmpty()){
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}