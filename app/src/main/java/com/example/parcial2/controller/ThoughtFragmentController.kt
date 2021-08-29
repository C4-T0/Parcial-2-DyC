package com.example.parcial2.controller

import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.parcial2.model.LocalStorage
import com.example.parcial2.model.daos.ThoughtDao
import com.example.parcial2.view.MainActivity
import com.example.parcial2.view.fragments.ThoughtFragment

class ThoughtFragmentController {
    private lateinit var thoughtDao: ThoughtDao

    fun deleteThought(
        mainActivity: MainActivity,
        thoughtFragment: ThoughtFragment
    ) {

        thoughtFragment.thought
        AlertDialog.Builder(mainActivity).setMessage("¿Estas seguro de eliminar este pensamiento?")
            .setTitle("Alerta")
            .setPositiveButton("Eliminar") { _, _ ->
                mainActivity.saveInstanceMemento()//TODO
                this.thoughtDao =
                    LocalStorage.getLocalStorage(mainActivity.applicationContext).thoughtDao()
                this.thoughtDao.delete(thoughtFragment.thought)
                thoughtFragment.confirmDelete()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }
            .create().show()

    }

    fun editThought(
        mainActivity: MainActivity,
        thoughtFragment: ThoughtFragment
    ): Boolean {
        var error = false

        val title =thoughtFragment.editTitleTextView.text
        val description = thoughtFragment.editDescriptionTextView.text

        if ( title.length > 100) {
            mainActivity.showAlertError("Error", "No se puede exceder los 100 caracteres")
            error = true
        }
        if (title.isEmpty()) {
            mainActivity.showAlertError("Error", "Se debe poner un titulo")
            error = true
        }
        if (description.isEmpty()) {
            mainActivity.showAlertError("Error", "Se debe poner una descripción")
            error = true
        }
        if (error) {
            return false
        }

        mainActivity.saveInstanceMemento()

        thoughtFragment.apply {
            titleTextView.text = title
            descriptionTextView.text = description
            expandedViewTitleView.text = title
            editLayout.visibility = View.GONE
            expandLayout.visibility = View.VISIBLE
            baseLayout.visibility = View.GONE
        }

        thoughtFragment.thought.apply {
            setTitle(title.toString())
            setDescription(description.toString())
        }

        this.thoughtDao = LocalStorage.getLocalStorage(mainActivity.applicationContext).thoughtDao()
        this.thoughtDao.update(thoughtFragment.thought)

        return true
    }
}