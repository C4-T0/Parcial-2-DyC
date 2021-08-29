package com.example.parcial2.view.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.parcial2.R
import com.example.parcial2.controller.ThoughtFragmentController
import com.example.parcial2.model.pojo.Thought
import com.example.parcial2.view.MainActivity
import java.sql.Date
import java.text.SimpleDateFormat


class ThoughtFragment : Fragment() {

    lateinit var thought: Thought //<----

    lateinit var rootView: View

    lateinit var titleTextView: TextView
    lateinit var descriptionTextView: TextView

    lateinit var expandedViewTitleView: TextView

    lateinit var dateViewExpandedView: TextView
    lateinit var dateViewPreviewView: TextView
    lateinit var cardViewThoughtFragment: CardView
    lateinit var expandLayout: ConstraintLayout
    lateinit var baseLayout: LinearLayout
    lateinit var editLayout: LinearLayout
    lateinit var deletePreviewThought: ImageButton
    lateinit var editPreviewThought: ImageButton

    lateinit var editTitleTextView: TextView
    lateinit var editDescriptionTextView: TextView

    lateinit var cancelEditThoughtFragment: ImageButton
    lateinit var saveEditThoughtFragment: ImageButton

    lateinit var editThoughtExpandedView: ImageButton
    lateinit var deleteThoughtExpandedView: ImageButton

    private lateinit var thoughtFragmentController: ThoughtFragmentController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thoughtFragmentController = ThoughtFragmentController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_thought, container, false)
        cardViewThoughtFragment = rootView.findViewById(R.id.card_view_thought)
        titleTextView = rootView.findViewById(R.id.textViewFragment)
        descriptionTextView = rootView.findViewById(R.id.description_thought_fragment)
        expandLayout = rootView.findViewById(R.id.expand_layout_fragment)
        baseLayout = rootView.findViewById(R.id.base_layout_fragment)
        editLayout = rootView.findViewById(R.id.edit_thought_layout_fragment)
        deletePreviewThought = rootView.findViewById(R.id.delete_fragment_thought)
        editPreviewThought = rootView.findViewById(R.id.edit_fragment_thought)
        expandedViewTitleView = rootView.findViewById(R.id.title_view_expaned_view_fragment)
        dateViewExpandedView = rootView.findViewById(R.id.date_view_expanded_view_thought)
        dateViewPreviewView = rootView.findViewById(R.id.date_view_preview_view_thought)
        editTitleTextView = rootView.findViewById(R.id.edit_title_thought_fragment)
        editDescriptionTextView = rootView.findViewById(R.id.edit_description_fragment_thought)
        cancelEditThoughtFragment = rootView.findViewById(R.id.cancel_edit_thought_fragment)
        saveEditThoughtFragment = rootView.findViewById(R.id.save_edit_thought_fragment)
        editThoughtExpandedView = rootView.findViewById(R.id.edit_thought_expanded_view_fragment)
        deleteThoughtExpandedView =
            rootView.findViewById(R.id.delete_thought_expanded_view_fragment)

        expandedViewTitleView.text = thought.getTitle()
        titleTextView.text = thought.getTitle()
        descriptionTextView.text = thought.getDescription()

        val formatDate = SimpleDateFormat("dd-MM-yyyy").format(Date(thought.getCreationDate()))

        dateViewPreviewView.text = formatDate
        dateViewExpandedView.text = formatDate

        cardViewThoughtFragment.setCardBackgroundColor(
            when (thought.getCategory()) {
                1 -> Color.parseColor("#C39BD3")
                2 -> Color.parseColor("#7FB3D5")
                3 -> Color.parseColor("#48C9B0")
                4 -> Color.parseColor("#F7DC6F")
                5 -> Color.parseColor("#DC7633")
                else -> Color.LTGRAY
            }
        )
        expandLayout.setOnClickListener { expandView() }
        baseLayout.setOnClickListener { expandView() }
        deletePreviewThought.setOnClickListener { selfDelete() }
        editPreviewThought.setOnClickListener { editThought() }
        cancelEditThoughtFragment.setOnClickListener { cancelEdit() }
        saveEditThoughtFragment.setOnClickListener { saveEdit() }
        editThoughtExpandedView.setOnClickListener { editThought() }
        deleteThoughtExpandedView.setOnClickListener { selfDelete() }
        return rootView
    }

    fun expandView() {
        if (expandLayout.visibility == View.GONE) {
            expandLayout.visibility = View.VISIBLE
            baseLayout.visibility = View.GONE
        } else {
            expandLayout.visibility = View.GONE
            baseLayout.visibility = View.VISIBLE
        }
    }

    fun selfDelete() {
        thoughtFragmentController.deleteThought(activity as MainActivity, this)
    }

    fun confirmDelete() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun editThought() {
        editTitleTextView.text = thought.getTitle()
        editDescriptionTextView.text = thought.getDescription()

        editLayout.visibility = View.VISIBLE
        expandLayout.visibility = View.GONE
        baseLayout.visibility = View.GONE
    }

    fun cancelEdit() {
        editLayout.visibility = View.GONE
        expandLayout.visibility = View.VISIBLE
        baseLayout.visibility = View.GONE

        hideKeyboardFrom(requireContext(), rootView)
    }

    fun saveEdit() {
        if (thoughtFragmentController.editThought(activity as MainActivity, this)) {
            hideKeyboardFrom(requireContext(), rootView)
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance(thought: Thought): ThoughtFragment {
            return ThoughtFragment().apply {
                this.thought = thought
            }
        }
    }
}