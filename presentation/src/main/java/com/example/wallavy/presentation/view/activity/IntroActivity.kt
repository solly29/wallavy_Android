package com.example.wallavy.presentation.view.activity

import android.content.Intent
import com.example.wallavy.presentation.R
import com.example.wallavy.presentation.base.BaseActivity
import com.example.wallavy.presentation.base.BaseViewModel
import com.example.wallavy.presentation.databinding.ActivityIntroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity: BaseActivity<BaseViewModel, ActivityIntroBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_intro
    override val viewModel = BaseViewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            startActivity(
                Intent(this@IntroActivity, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}