package com.example.parcial2.controller

import com.example.parcial2.model.LocalStorage
import com.example.parcial2.model.daos.ThoughtDao
import com.example.parcial2.model.pojo.Thought
import com.example.parcial2.view.MainActivity


class MainActivityController {

    private lateinit var thoughtDao: ThoughtDao

    fun getAllThoughts(mainActivity: MainActivity): List<Thought> {

        this.thoughtDao = LocalStorage.getLocalStorage(mainActivity.applicationContext).thoughtDao()

        return thoughtDao.all
    }

    fun updateAllThoughts(mainActivity: MainActivity, list:List<Thought>){

        this.thoughtDao = LocalStorage.getLocalStorage(mainActivity.applicationContext).thoughtDao()

        this.thoughtDao.macheteandoAndo()

        this.thoughtDao.insertAll(list)

    }
}