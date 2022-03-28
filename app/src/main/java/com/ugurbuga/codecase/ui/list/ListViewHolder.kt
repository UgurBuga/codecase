package com.ugurbuga.codecase.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import com.ugurbuga.codecase.base.BaseViewHolder
import com.ugurbuga.codecase.databinding.ItemProductBinding
import com.ugurbuga.codecase.domain.model.ProductUIModel
import com.ugurbuga.codecase.extensions.executeAfter

class ListViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : BaseViewHolder<ItemProductBinding>(
    binding = ItemProductBinding.inflate(inflater, parent, false)
) {
    fun bind(
        product: ProductUIModel,
        onCastClicked: ((productItem: ProductUIModel, imageView: AppCompatImageView) -> Unit)?
    ) {
        binding.executeAfter {
            this.item = product

            ViewCompat.setTransitionName(image, product.name)

            root.setOnClickListener { onCastClicked?.invoke(product, image) }
        }
    }
}
