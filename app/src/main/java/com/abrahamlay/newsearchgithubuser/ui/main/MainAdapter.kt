package com.abrahamlay.newsearchgithubuser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abrahamlay.domain.common.ResultState
import com.abrahamlay.domain.entity.SearchResultEntity
import com.abrahamlay.newsearchgithubuser.R
import com.abrahamlay.newsearchgithubuser.databinding.ItemProgressBarBinding
import com.abrahamlay.newsearchgithubuser.databinding.ItemSearchResultBinding
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_progress_bar.view.*

/**
 * Created by Abraham Lay on 2019-11-22.
 */
class MainAdapter :
    PagedListAdapter<SearchResultEntity.ItemsItem, RecyclerView.ViewHolder>(UsersDiffCallback()) {

    private var networkState: ResultState<*>? = null

    private val clickSubject = PublishSubject.create<SearchResultEntity.ItemsItem>()
    val albumItemClickEvent: Observable<SearchResultEntity.ItemsItem> = clickSubject

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_PROGRESS) {
            NetworkStateItemViewHolder(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_progress_bar, parent, false
                ) as ItemProgressBarBinding
            )
        } else {
            DataHolder(
                DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result, parent, false
                ) as ItemSearchResultBinding
            )
        }
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is DataHolder) {
            val searchItem = getItem(position)
            searchItem?.let { viewHolder.bind(searchItem) }
        } else {
            (viewHolder as NetworkStateItemViewHolder).bindView(networkState)
        }


    }

    inner class DataHolder(private var itemSearchBinding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(itemSearchBinding.root), View.OnClickListener {

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


    inner class NetworkStateItemViewHolder(itemView: ItemProgressBarBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bindView(networkState: ResultState<*>?) {
            if (networkState != null && networkState is ResultState.Loading<*>) {
                itemView.progress_bar.visibility = View.VISIBLE
            } else {
                itemView.progress_bar.visibility = View.GONE
            }

            if (networkState != null && networkState is ResultState.Error<*>) {
                Toast.makeText(
                    itemView.context,
                    networkState.throwable.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState is ResultState.Loading<*>
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    fun setNetworkState(newNetworkState: ResultState<*>) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
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

    companion object {
        private const val TYPE_PROGRESS = 0
        private const val TYPE_ITEM = 1
    }
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}