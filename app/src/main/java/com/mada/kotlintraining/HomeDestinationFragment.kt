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


class HomeDestinationFragment : Fragment(R.layout.fragment_home_destination) {

    private var viewModel: SecondFragmentViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var punkApi = PunkApi.create().getBeers()
        viewModel =
            activity?.let { ViewModelProviders.of(it).get(SecondFragmentViewModel::class.java) }
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


}