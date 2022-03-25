package com.ugurbuga.codecase.ui.detail

import androidx.navigation.fragment.findNavController
import com.ugurbuga.codecase.R
import com.ugurbuga.codecase.base.BaseFragment
import com.ugurbuga.codecase.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override fun viewModelClass() = DetailViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_detail

    override fun onInitDataBinding() {
        viewBinding.detailText.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}