package com.example.mvvmtest.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.repository.DiscoverRepository
import com.example.mvvmtest.util.DiscoverAction

import javax.inject.Inject

class DiscoverViewModel : ViewModel() {

    @Inject
    lateinit var flechPreferences: FlechPreferences

    @Inject
    lateinit var discoverRepository: DiscoverRepository

    var users: MutableLiveData<List<DiscoverUser>>

    init {
        ApiController.getAppComponent().inject(this)
        users = MutableLiveData()
    }

    fun onUserAction(idUser: Int, action: DiscoverAction) {
        val list = users.value
        for (user in list!!) {
            if (user.idUser == idUser) {
                //aqui deberia borrar
                //todo averiguar como borrar de la lista
                break
            }
        }
        users.setValue(list)
    }

    fun getDiscoverUser() {
        users = discoverRepository.getDiscoverUsers()
    }

    override fun onCleared() {
        super.onCleared()
    }

}
