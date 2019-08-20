package com.example.jjp.hotels.modules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.jjp.hotels.modules.hotel.HotelFragment
import com.example.jjp.hotels.modules.list.HotelsListFragment
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.example.jjp.hotels.R
import kotlinx.android.synthetic.main.activity_main.rootContainer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHotelsListFragment()
    }

    private fun showHotelsListFragment() {
        replaceFragment(R.id.fragmentContainer, HotelsListFragment(), HotelsListFragment.TAG, false)
    }

    fun showHotelFragment() {
        replaceFragment(R.id.fragmentContainer, HotelFragment(), HotelFragment.TAG)
    }

    private fun replaceFragment(
        container: Int,
        fragment: Fragment,
        fragmentTag: String,
        needBackStack: Boolean = true
    ) {
        if (supportFragmentManager.findFragmentByTag(fragmentTag) == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(container, fragment)
            if (needBackStack) {
                fragmentTransaction.addToBackStack(fragmentTag)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    fun showError(error: String) {
        Snackbar.make(
            rootContainer,
            error,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        onCreate(savedInstanceState)
    }
}
