package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dogs.R
import com.example.dogs.viewmodel.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        view.findViewById<RecyclerView>(R.id.dogsList)?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener {
            view.findViewById<RecyclerView>(R.id.dogsList).visibility = View.GONE
            view.findViewById<TextView>(R.id.listError).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.loadingView).visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.dogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogs?.let {
                view?.findViewById<RecyclerView>(R.id.dogsList)?.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                view?.findViewById<TextView>(R.id.listError)?.visibility =
                    if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                view?.findViewById<ProgressBar>(R.id.loadingView)?.visibility =
                    if(it) View.VISIBLE else View.GONE
                if(it) {
                    view?.findViewById<TextView>(R.id.listError)?.visibility = View.GONE
                    view?.findViewById<RecyclerView>(R.id.dogsList)?.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionSettings -> {
                view?.let {Navigation.findNavController(it).navigate(ListFragmentDirections.actionSettings()) }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}