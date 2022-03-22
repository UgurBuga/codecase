package com.ugurbuga.turkcellcodecase.ui.list

import androidx.navigation.fragment.findNavController
import com.ugurbuga.turkcellcodecase.R
import com.ugurbuga.turkcellcodecase.base.BaseFragment
import com.ugurbuga.turkcellcodecase.databinding.FragmentListBinding

class ListFragment : BaseFragment<ListViewModel, FragmentListBinding>() {

    override fun viewModelClass() = ListViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_list

    override fun onInitDataBinding() {
        viewBinding.listText.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_detail)
        }
    }

}