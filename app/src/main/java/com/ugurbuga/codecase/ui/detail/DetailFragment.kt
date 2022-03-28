package com.ugurbuga.codecase.ui.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ugurbuga.codecase.R
import com.ugurbuga.codecase.base.BaseFragment
import com.ugurbuga.codecase.bindings.setImageUrl
import com.ugurbuga.codecase.databinding.FragmentDetailBinding
import com.ugurbuga.codecase.domain.model.ProductDetailUIModel
import com.ugurbuga.codecase.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override fun viewModelClass() = DetailViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_detail

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onInitDataBinding() {
        viewBinding.image.setImageUrl(args.imageUrl)
        observe(viewModel.detail, ::onDetail)
    }

    private fun onDetail(productDetail: ProductDetailUIModel) {
        viewBinding.productDetail = productDetail
    }

}