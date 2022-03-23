package com.ugurbuga.turkcellcodecase.ui.list

import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.ugurbuga.turkcellcodecase.R
import com.ugurbuga.turkcellcodecase.base.BaseFragment
import com.ugurbuga.turkcellcodecase.databinding.FragmentListBinding
import com.ugurbuga.turkcellcodecase.domain.ProductUIModel
import com.ugurbuga.turkcellcodecase.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListViewModel, FragmentListBinding>() {

    override fun viewModelClass() = ListViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_list

    lateinit var adapter: ProductListAdapter

    override fun onInitDataBinding() {
        observe(viewModel.list, ::onListChanged)

        adapter = ProductListAdapter(::onProductClicked)
        viewBinding.listRecyclerView.adapter = adapter
    }

    private fun onProductClicked(
        product: ProductUIModel,
        imageView: AppCompatImageView
    ) {
        Toast.makeText(requireContext(), product.name, Toast.LENGTH_SHORT).show()
    }

    private fun onListChanged(list: List<ProductUIModel>) {
        adapter.submitList(list)
    }

}