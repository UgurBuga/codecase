package com.ugurbuga.codecase.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ugurbuga.codecase.extensions.collect
import com.ugurbuga.codecase.extensions.getString
import com.ugurbuga.codecase.extensions.observe

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    private lateinit var dialog: Dialog
    protected lateinit var viewBinding: DB

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getResourceLayoutId(), container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    abstract fun viewModelClass(): Class<VM>

    @LayoutRes
    protected abstract fun getResourceLayoutId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass()]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())
        onInitDataBinding()
        collect(viewModel.baseEvent, ::onBaseEvent)
    }

    private fun onBaseEvent(event: BaseViewEvent) {
        when (event) {
            BaseViewEvent.DismissLoading -> {
                dialog.dismiss()
            }
            is BaseViewEvent.ShowErrorMessage -> {
                Toast.makeText(
                    requireContext(), event.message.getString(requireContext()), Toast.LENGTH_SHORT
                ).show()
            }
            BaseViewEvent.ShowLoading -> {
                dialog.setTitle("Loading")
                dialog.show()

            }
        }
    }

    abstract fun onInitDataBinding()

}