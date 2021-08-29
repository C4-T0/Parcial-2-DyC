package com.example.parcial2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ScrollView
import androidx.appcompat.app.AlertDialog
import com.example.parcial2.R
import com.example.parcial2.controller.MainActivityController
import com.example.parcial2.controller.Memento.CareTaker
import com.example.parcial2.controller.Memento.MementoListThought
import com.example.parcial2.model.pojo.Thought
import com.example.parcial2.view.fragments.ReportThoughtFragment
import com.example.parcial2.view.fragments.ThoughtFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var thoughtScrollView: ScrollView

    private lateinit var careTaker: CareTaker

    private lateinit var mainActivityController: MainActivityController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityController = MainActivityController()
        careTaker = CareTaker()
        fab = findViewById(R.id.fab)

        thoughtScrollView = findViewById(R.id.scroll_view_thought)
        fab.setOnClickListener { fabClick() }

        val allThoughts = mainActivityController.getAllThoughts(this)


        reDraw(allThoughts)

    }

    fun saveInstanceMemento() {
        careTaker.addUndoElement(MementoListThought(mainActivityController.getAllThoughts(this)))
    }

    private fun saveUndoInstanceMemento() {
        careTaker.addRedoElement(MementoListThought(mainActivityController.getAllThoughts(this)))
    }

    fun reDraw(list: List<Thought>) {
        for (thought in list) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.thoughtLinearLayout,
                    ThoughtFragment.newInstance(
                        thought
                    )
                ).commit()
        }
    }

    private fun backButtonTest() {

        if (careTaker.mementoLengthList > 0) {

            saveUndoInstanceMemento()

            for (frag in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(frag).commit()
            }
            val prevMementoList = careTaker.undoMemento().state
            mainActivityController.updateAllThoughts(this, prevMementoList)

            reDraw(prevMementoList)
        }
    }

    private fun redoButtonTest() {
        if (careTaker.redoMementoLengthList > 0) {
            for (frag in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(frag).commit()
            }
            val prevMementoList =
                careTaker.redoMemento(MementoListThought(mainActivityController.getAllThoughts(this))).state
            mainActivityController.updateAllThoughts(this, prevMementoList)
            reDraw(prevMementoList)
        }
    }

    fun showAlertError(title: String, message: String) {
        AlertDialog.Builder(this).setMessage(message)
            .setTitle(title)
            .setPositiveButton("OK") { dialog, _ -> dialog.cancel() }.create().show()
    }

    private fun fabClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.report_thought_layout, ReportThoughtFragment.newInstance()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.undo_button_menu -> {
                backButtonTest()
                return true
            }
            R.id.redo_button_menu -> {
                redoButtonTest()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


}