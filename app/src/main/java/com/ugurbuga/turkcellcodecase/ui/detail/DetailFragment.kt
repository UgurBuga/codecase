package com.ugurbuga.turkcellcodecase.ui.detail

import androidx.navigation.fragment.findNavController
import com.ugurbuga.turkcellcodecase.R
import com.ugurbuga.turkcellcodecase.base.BaseFragment
import com.ugurbuga.turkcellcodecase.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override fun viewModelClass() = DetailViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_detail

    override fun onInitDataBinding() {
        viewBinding.detailText.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}