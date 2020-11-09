package com.mada.kotlintraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mada.kotlintraining.models.BeerFromPlugin
import com.mada.kotlintraining.models.BeerFromPluginItem
import kotlinx.android.synthetic.main.fragment_item_detail.*


class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            activity?.let { ViewModelProvider(it).get(SecondFragmentViewModel::class.java) }!!


        var beers = viewModel.listOfBeer.value
        var beer =
            beers?.get(arguments?.get("position") as Int)?.get(arguments?.get("position") as Int)

        Glide.with(context).load(beer?.image_url)
            .apply(RequestOptions().centerCrop())
            .apply(RequestOptions().centerInside())
            .into(imageView)
        textView.text = beer?.name
        textView2.text = beer?.description
        textView3.text = beer?.brewers_tips


    }

}