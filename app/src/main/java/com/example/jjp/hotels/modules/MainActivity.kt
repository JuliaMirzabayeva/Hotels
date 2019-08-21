package com.example.jjp.hotels.modules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jjp.hotels.modules.hotel.HotelFragment
import com.example.jjp.hotels.modules.list.HotelsListFragment
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.example.jjp.hotels.R
import kotlinx.android.synthetic.main.activity_main.rootContainer

class MainActivity : AppCompatActivity(), NavigationRouter, ErrorHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHotelsListFragment()
    }

    private fun showHotelsListFragment() {
        addFragment(R.id.fragmentContainer, HotelsListFragment(), HotelsListFragment.TAG, false)
    }

    override fun showHotelFragment() {
        addFragment(R.id.fragmentContainer, HotelFragment(), HotelFragment.TAG)
    }

    private fun addFragment(
        container: Int,
        fragment: Fragment,
        fragmentTag: String,
        needBackStack: Boolean = true
    ) {
        if (supportFragmentManager.findFragmentByTag(fragmentTag) == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(container, fragment)
            if (needBackStack) {
                fragmentTransaction.addToBackStack(fragmentTag)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    override fun showError(error: String) {
        Snackbar.make(
            rootContainer,
            error,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
