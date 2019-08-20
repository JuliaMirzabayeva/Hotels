package com.example.jjp.hotels.modules

import com.example.jjp.hotels.R
import nucleus.presenter.Presenter
import nucleus.view.NucleusSupportFragment

abstract class BaseFragment<P : Presenter<*>> : NucleusSupportFragment<P>() {

    fun showError(error: String?) {
        (activity as MainActivity).showError(error ?: getString(R.string.error))
    }
}
