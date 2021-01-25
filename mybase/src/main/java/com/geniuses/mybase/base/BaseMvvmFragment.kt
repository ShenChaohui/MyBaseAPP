package com.geniuses.mybase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * Created by Sch.
 * Date: 2020/12/21
 * description:
 */
open abstract class BaseMvvmFragment<VM : ViewModel, VDB : ViewDataBinding> : Fragment() {
    protected lateinit var mViewModel: VM
    protected lateinit var mDataBinding: VDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this).get(getClass(this))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mDataBinding.lifecycleOwner = this
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    private fun <T> getClass(t: Any): Class<T> {
        return (t.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
    }
}