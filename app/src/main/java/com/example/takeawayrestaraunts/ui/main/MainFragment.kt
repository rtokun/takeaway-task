package com.example.takeawayrestaraunts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.takeawayrestaraunts.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.progressBar
import kotlinx.android.synthetic.main.main_fragment.restaurantsList
import kotlinx.android.synthetic.main.main_fragment.rootContainer
import kotlinx.android.synthetic.main.main_fragment.swipeRefreshContainer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel<MainViewModelImpl>()

    private val adapter: RestaurantsAdapter by inject {
        RestaurantsAdapter.getInjectorDefinitionParameters(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    updateIsLoading(it)
                }
            })
            error.observe(viewLifecycleOwner, Observer {
                it?.let {
                    Snackbar.make(rootContainer, it, BaseTransientBottomBar.LENGTH_LONG)
                        .show()
                }
            })
            restaurants.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitItems(it)
                }
            })
        }
    }

    private fun initViews() {
        restaurantsList.adapter = adapter
    }

    private fun updateIsLoading(isLoading: Boolean) {
        swipeRefreshContainer.visibility = if (isLoading) View.GONE else View.VISIBLE
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}