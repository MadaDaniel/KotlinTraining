package com.mada.kotlintraining

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mada.kotlintraining.models.BeerFromPlugin
import com.mada.kotlintraining.request.PunkApi
import kotlinx.android.synthetic.main.fragment_home_destination.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [HomeDestinationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeDestinationFragment : Fragment(R.layout.fragment_home_destination) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var viewModel: SecondFragmentViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment



    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var punkApi = PunkApi.create().getBeers()
        viewModel = activity?.let { ViewModelProviders.of(it).get(SecondFragmentViewModel::class.java) }
        var listaUtilita = mutableListOf<BeerFromPlugin>()
        homeButton.setOnClickListener { v ->
            punkApi.enqueue(object : Callback<BeerFromPlugin> {
                override fun onResponse(
                    call: Call<BeerFromPlugin>,
                    response: Response<BeerFromPlugin>
                ) {

                    if (response.isSuccessful) {
                        var listOfBeers = response.body()

                        for (beer in listOfBeers!!) {
                            listaUtilita.add(listOfBeers)
                            println(beer.toString())
                        }
                    }

                    viewModel?.setListOfBeer(listaUtilita)
                    v.findNavController().navigate(R.id.secondDestinationFragment)
                }

                override fun onFailure(call: Call<BeerFromPlugin>, t: Throwable) {
                    println(call.toString())
                    println(t.message)
                    println(t.stackTrace)
                    println(t.cause)
                }

            })
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeDestinationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeDestinationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}