package com.example.jjp.hotels.modules.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.jjp.hotels.dagger.App
import com.example.jjp.hotels.modules.list.adapters.HotelsListAdapter
import com.example.jjp.hotels.modules.list.vo.HotelsListVO
import kotlinx.android.synthetic.main.fragment_hotels_list.*
import android.view.MenuInflater
import com.example.jjp.hotels.R
import com.example.jjp.hotels.models.list.HotelsListProvider
import com.example.jjp.hotels.modules.BaseFragment
import com.example.jjp.hotels.modules.NavigationRouter
import java.security.InvalidParameterException

class HotelsListFragment : BaseFragment<HotelsListPresenter>(), HotelListView {

    private lateinit var hotelsListAdapter: HotelsListAdapter

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setHasOptionsMenu(true)

        setPresenterFactory {
            App.component().provideHotelsListPresenter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.menu_hotels_list_sort, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hotels_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        swipeContainer.setOnRefreshListener(::loadHotels)
    }

    private fun initAdapter() {
        hotelsListAdapter = HotelsListAdapter(this::selectHotel)
        hotelsRecyclerView.layoutManager = LinearLayoutManager(activity?.baseContext)
        hotelsRecyclerView.adapter = hotelsListAdapter
        hotelsRecyclerView.addItemDecoration(HotelsListItemDecoration(requireContext()))
    }

    private fun loadHotels() {
        presenter?.loadHotels()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val sortType = when (item?.itemId) {
            R.id.defaultSort -> HotelsListProvider.SortType.DEFAULT
            R.id.distanceSort -> HotelsListProvider.SortType.DISTANCE
            R.id.suitsSort -> HotelsListProvider.SortType.SUITS
            else -> throw InvalidParameterException("Unknown menu item: " + item?.itemId)
        }
        presenter.sortHotels(sortType)
        return true
    }

    private fun selectHotel(hotelId: Long) {
        presenter?.setCurrentHotel(hotelId)
        (activity as NavigationRouter).showHotelFragment()
    }

    override fun setProgressVisibility(isVisible: Boolean) {
        swipeContainer.isRefreshing = isVisible
    }

    override fun setHotels(hotels: List<HotelsListVO>) {
        hotelsListAdapter.setHotels(hotels)
    }

    companion object {
        const val TAG = "hotels_list_fragment"
    }
}