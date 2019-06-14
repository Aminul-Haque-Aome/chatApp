package com.remotearth.fake_coder.chatapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initDataBinding(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        bundleCommunication()
    }

    protected fun showToast(message: String) {
        mContext?.let { Toast.makeText(mContext, message, Toast.LENGTH_LONG).show() }
    }

    protected fun showSnackBar(message: String, parentLayout: View) {
        mContext?.let { Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show() }
    }

    protected fun navigateTo(actionId: Int, bundle: Bundle? = null) {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(actionId, bundle)
    }

    protected abstract fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    protected abstract fun initViewModel()
    protected abstract fun bundleCommunication()
    protected abstract fun initialization()
}