package com.mada.kotlintraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mada.kotlintraining.models.BeerFromPlugin
import kotlinx.android.synthetic.main.fragment_second_destination.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var viewModel: SecondFragmentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SecondDestinationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondDestinationFragment : Fragment(), RecyclerAdapter.OnItemClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var recyclerView: RecyclerView? = null
    var recyclerAdapter: RecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondDestinationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondDestinationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {
        view?.let {
            Snackbar.make(it, "It works $position", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}