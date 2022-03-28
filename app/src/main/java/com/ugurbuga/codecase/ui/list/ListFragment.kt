package com.ugurbuga.codecase.ui.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ugurbuga.codecase.R
import com.ugurbuga.codecase.base.BaseFragment
import com.ugurbuga.codecase.databinding.FragmentListBinding
import com.ugurbuga.codecase.domain.model.ProductUIModel
import com.ugurbuga.codecase.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListViewModel, FragmentListBinding>() {

    override fun viewModelClass() = ListViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_list

    lateinit var adapter: ProductListAdapter

    override fun onInitDataBinding() {
        observe(viewModel.list, ::onListChanged)
        observe(viewModel.viewState, ::onViewState)

        adapter = ProductListAdapter(::onProductClicked)
        viewBinding.listRecyclerView.adapter = adapter
    }

    private fun onViewState(listViewState: ListViewState) {
        viewBinding.viewState = listViewState
    }

    private fun onProductClicked(product: ProductUIModel, imageView: AppCompatImageView) {

        val extras = FragmentNavigatorExtras(imageView to getString(R.string.image_detail))
        val directions = ListFragmentDirections.actionListToDetail(product.productId, product.image)

        findNavController().navigate(directions.actionId, directions.arguments, null, extras)
    }

    private fun onListChanged(list: List<ProductUIModel>) {
        adapter.submitList(list)
    }

}