package com.example.parcial2.controller

import com.example.parcial2.model.LocalStorage
import com.example.parcial2.model.daos.ThoughtDao
import com.example.parcial2.model.pojo.Thought
import com.example.parcial2.view.MainActivity

class ReportThoughtFragmentController {

    private lateinit var thoughtDao: ThoughtDao

    fun reportThought(mainActivity: MainActivity, thought: Thought): Boolean {

        var error = false

        if (thought.getTitle().length > 100) {
            mainActivity.showAlertError("Error", "No se puede exceder los 100 caracteres")
            error = true
        }
        if (thought.getTitle().isEmpty()) {
            mainActivity.showAlertError("Error", "Se debe poner un titulo")
            error = true
        }
        if (thought.getDescription().isEmpty()) {
            mainActivity.showAlertError("Error", "Se debe poner una descripción")
            error = true
        }
        if(thought.getCategory()==-1){
            mainActivity.showAlertError("Error", "Se debe elegir una categoría")
            error = true
        }

        if (error) {
            return false
        }

        mainActivity.saveInstanceMemento() //TODO

        this.thoughtDao = LocalStorage.getLocalStorage(mainActivity.applicationContext).thoughtDao()

        thoughtDao.insert(thought)

        return true
    }
}