package com.geniuses.mybase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType


/**
 * Created by Sch.
 * Date: 2020/12/21
 * description:
 */
open abstract class BaseMvvmActivity<VM : AndroidViewModel, VDB : ViewDataBinding> :
    AppCompatActivity() {
    protected lateinit var mViewModel: VM
    protected lateinit var mDataBinding: VDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mDataBinding.lifecycleOwner = this
        mViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                getClass(this)
            )
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