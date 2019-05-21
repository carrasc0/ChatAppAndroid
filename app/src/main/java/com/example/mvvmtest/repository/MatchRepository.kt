package com.example.mvvmtest.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.network.RetrofitCall
import java.util.ArrayList
import javax.inject.Inject

class MatchRepository {

    @Inject
    lateinit var retrofitCall: RetrofitCall

    private val dataSet = ArrayList<Match>()

    fun getMatches() : ArrayList<Match>{
        dataSet.add(Match(1, "08/06/1990", 2, "R.drawable.image1", "Rebeca", 28, "Hola, que tal?"))
        dataSet.add(Match(2, "02/08/1991", 3, "R.drawable.image2", "Silvia", 25, "Hola, que tal? Como estas??"))
        return dataSet
    }
    /*private val dataSet : MutableLiveData<List<Match>> by lazy {
        MutableLiveData().also {
            loadMatch()
        }
    }

    init {
        ApiController.getAppComponent().inject(this)
    }



    private fun loadMatch() {
        dataSet.add(Match(1, "08/06/1990", 2, "R.drawable.image1", "Rebeca", 28, "Hola, que tal?"))
        dataSet.add(Match(2, "02/08/1991", 3, "R.drawable.image2", "Silvia", 25, "Hola, que tal? Como estas??"))
    }*/

}