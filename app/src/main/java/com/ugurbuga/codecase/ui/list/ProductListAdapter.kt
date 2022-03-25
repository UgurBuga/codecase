package com.ugurbuga.codecase.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.codecase.base.BaseListAdapter
import com.ugurbuga.codecase.domain.ProductUIModel

class ProductListAdapter(
    private val onProductClicked: ((item: ProductUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : BaseListAdapter<ProductUIModel>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return ListViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ListViewHolder) {
            val item = getItem(position) as ProductUIModel
            holder.bind(item, onProductClicked)
        }
    }
}