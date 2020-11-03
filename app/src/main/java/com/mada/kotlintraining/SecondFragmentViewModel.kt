package com.mada.kotlintraining

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mada.kotlintraining.models.BeerFromPlugin

class SecondFragmentViewModel() : ViewModel() {

    var listOfBeer: MutableLiveData<List<BeerFromPlugin>> = MutableLiveData()

    fun setListOfBeer(input: List<BeerFromPlugin>) {
        listOfBeer.value = input
    }

}