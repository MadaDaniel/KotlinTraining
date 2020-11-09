package com.mada.kotlintraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.BundleCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mada.kotlintraining.models.BeerFromPlugin
import kotlinx.android.synthetic.main.fragment_second_destination.*


lateinit var viewModel: SecondFragmentViewModel


class SecondDestinationFragment : Fragment(), RecyclerAdapter.OnItemClickListener {


    var recyclerView: RecyclerView? = null
    var recyclerAdapter: RecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            activity?.let { ViewModelProvider(it).get(SecondFragmentViewModel::class.java) }!!
        recyclerView = activity?.findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(context, this)
        recyclerview.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        recyclerView?.adapter = recyclerAdapter
        recyclerAdapter?.setBeerListItems(viewModel.listOfBeer.value!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onItemClick(position: Int) {
        view?.let {

            it.findNavController()
                .navigate(R.id.itemDetailFragment, bundleOf("position" to position))

        }
    }
}