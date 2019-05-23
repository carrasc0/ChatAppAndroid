package com.example.mvvmtest.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.R
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.network.RetrofitCall
import java.util.ArrayList
import javax.inject.Inject

class MatchRepository {

    @Inject
    lateinit var retrofitCall: RetrofitCall

    private var dataSet = ArrayList<Match>()

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun getMatches(): MutableLiveData<List<Match>> {
        setMatches()
        val data = MutableLiveData<List<Match>>()
        data.setValue(dataSet)
        return data
    }

    fun setMatches() {
        dataSet.add(Match(1, "08/06/1990", 2, R.drawable.image1, "Rebeca", 28, "Hola, que tal?"))
        dataSet.add(Match(2, "02/08/1991", 3, R.drawable.image2, "Silvia", 25, "Hola, que tal? Como estas??"))
        dataSet.add(Match(3, "02/08/1991", 3, R.drawable.image3, "Silvana", 25, "Hi"))
        dataSet.add(Match(4, "02/08/1991", 3, R.drawable.image4, "Margarita", 25, "Helloooo"))
        dataSet.add(Match(5, "02/08/1991", 3, R.drawable.image5, "Claudia", 25, "Todo fresa"))
    }

}