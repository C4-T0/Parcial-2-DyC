package com.example.parcial2.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.parcial2.R
import com.example.parcial2.controller.ReportThoughtFragmentController
import com.example.parcial2.model.pojo.Thought
import com.example.parcial2.view.MainActivity


class ReportThoughtFragment : Fragment() {

    private lateinit var rootView: View

    private lateinit var firstStepCancelReportThought: ImageButton
    private lateinit var secondStepCancelReportThought: ImageButton

    private lateinit var firsStepReportThoughtLinearLayout: LinearLayout
    private lateinit var secondStepReportThoughtLinearLayout: LinearLayout

    private lateinit var nextStepReportThought: ImageButton
    private lateinit var previousStepReportThought: ImageButton

    private lateinit var titleReportThoughtEditText: EditText
    private lateinit var descriptionReportThoughtEditText: EditText

    private lateinit var saveThoughtReport: ImageButton

    private lateinit var cardViewReportThoughtFragment: CardView

    private lateinit var colorRadioGroup: RadioGroup

    private lateinit var reportThoughtFragmentController: ReportThoughtFragmentController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportThoughtFragmentController = ReportThoughtFragmentController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_report_thought, container, false)
        firstStepCancelReportThought = rootView.findViewById(R.id.first_step_cancel_thought_report)
        firsStepReportThoughtLinearLayout = rootView.findViewById(R.id.first_step_thought_report)

        secondStepCancelReportThought =
            rootView.findViewById(R.id.second_step_cancel_thought_report)
        secondStepReportThoughtLinearLayout = rootView.findViewById(R.id.second_step_thought_report)

        nextStepReportThought = rootView.findViewById(R.id.next_step_thought_report)
        previousStepReportThought = rootView.findViewById(R.id.previus_step_thought_report)

        titleReportThoughtEditText = rootView.findViewById(R.id.report_thought_title_fragment)
        descriptionReportThoughtEditText =
            rootView.findViewById(R.id.report_thought_description_fragment)

        saveThoughtReport = rootView.findViewById(R.id.save_thought_report)

        colorRadioGroup = rootView.findViewById(R.id.color_radio_group)

        cardViewReportThoughtFragment = rootView.findViewById(R.id.report_thought_card_fragment)

        firstStepCancelReportThought.setOnClickListener { cancelReportThought() }
        secondStepCancelReportThought.setOnClickListener { cancelReportThought() }
        nextStepReportThought.setOnClickListener { nextStep() }
        previousStepReportThought.setOnClickListener { previousStep() }
        saveThoughtReport.setOnClickListener { saveThoughtReport() }
        colorRadioGroup.setOnCheckedChangeListener { _, checkedId -> changeCardColor(checkedId) }
        return rootView
    }

    private fun cancelReportThought() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun nextStep() {
        firsStepReportThoughtLinearLayout.visibility = View.GONE
        secondStepReportThoughtLinearLayout.visibility = View.VISIBLE
    }

    private fun previousStep() {
        firsStepReportThoughtLinearLayout.visibility = View.VISIBLE
        secondStepReportThoughtLinearLayout.visibility = View.GONE
    }

    private fun changeCardColor(id: Int) {
        cardViewReportThoughtFragment.setCardBackgroundColor(
            when (id) {
                R.id.color_radio_button_1 -> Color.parseColor("#C39BD3")
                R.id.color_radio_button_2 -> Color.parseColor("#7FB3D5")
                R.id.color_radio_button_3 -> Color.parseColor("#48C9B0")
                R.id.color_radio_button_4 -> Color.parseColor("#F7DC6F")
                R.id.color_radio_button_5 -> Color.parseColor("#DC7633")
                else -> Color.LTGRAY
            }
        )
    }

    private fun saveThoughtReport() {
        val thought = Thought()

        thought.setTitle(titleReportThoughtEditText.text.toString())
        thought.setCategory(temporalParseColor(colorRadioGroup.checkedRadioButtonId))
        thought.setDescription(descriptionReportThoughtEditText.text.toString())
        thought.setCreationDate(System.currentTimeMillis())

        if (reportThoughtFragmentController.reportThought(activity as MainActivity, thought)) {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(
                    R.id.thoughtLinearLayout,
                    ThoughtFragment.newInstance(thought)
                ).commit()
            this.cancelReportThought()
        }

    }

    private fun temporalParseColor(id: Int): Int {
        return when (id) {
            R.id.color_radio_button_1 -> 1
            R.id.color_radio_button_2 -> 2
            R.id.color_radio_button_3 -> 3
            R.id.color_radio_button_4 -> 4
            R.id.color_radio_button_5 -> 5
            else -> -1
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReportThoughtFragment()
    }
}