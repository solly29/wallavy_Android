package com.example.wallavy.presentation.view.activity

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.wallavy.presentation.R
import com.example.wallavy.presentation.base.BaseActivity
import com.example.wallavy.presentation.base.BaseViewModel
import com.example.wallavy.presentation.databinding.ActivityMainBinding
import com.example.wallavy.presentation.model.AppBarData
import com.example.wallavy.presentation.view.adapter.ViewPagerAdapter
import com.example.wallavy.presentation.view.fragment.ChatFragment
import com.example.wallavy.presentation.view.fragment.HomeFragment
import com.example.wallavy.presentation.view.fragment.ProfileFragment

class MainActivity: BaseActivity<BaseViewModel, ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel = BaseViewModel()

    override fun initStartView() {
        binding.appbar = AppBarData(title = getString(R.string.app_name))
        val fragmentList = listOf(HomeFragment(), ChatFragment(), ProfileFragment())

        val pagerAdapter = ViewPagerAdapter(this, fragmentList)

        binding.mainViewpager.adapter = pagerAdapter
    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        with(binding) {
            mainViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mainBottomNavi.menu.getItem(position).isChecked = true
                }
            })

            mainBottomNavi.setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.item_home -> selectPage(0)
                    R.id.item_chat -> selectPage(1)
                    R.id.item_profile -> selectPage(2)
                    else -> false
                }
            }
        }
    }

    private fun selectPage(position: Int): Boolean {
        binding.mainViewpager.currentItem = position
        return true
    }
}