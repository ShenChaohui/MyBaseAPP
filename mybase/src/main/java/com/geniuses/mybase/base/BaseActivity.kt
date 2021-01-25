package com.geniuses.mybase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Sch.
 * Date: 2020/12/22
 * description:
 */
abstract class  BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()
}