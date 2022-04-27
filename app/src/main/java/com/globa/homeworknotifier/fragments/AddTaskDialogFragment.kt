package com.globa.homeworknotifier.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.interfaces.AddTaskDialogInterface
import com.globa.homeworknotifier.model.Subject
import com.globa.homeworknotifier.model.Task
import java.lang.ClassCastException
import java.util.*

class AddTaskDialogFragment : DialogFragment(){
    lateinit var taskInterface : AddTaskDialogInterface
    lateinit var timePicker: TimePicker
    lateinit var datePicker: DatePicker
    lateinit var taskSubjectEditText : EditText
    lateinit var taskTitleEditText : EditText
    lateinit var taskDescriptionEditText : EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity.let {
            val builder = AlertDialog.Builder(this.requireContext())
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.add_task_dialog_fragment, null)
            loadViews(view)
            builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.taskDialogConfirmTask,
                    DialogInterface.OnClickListener { dialog, id ->
                        taskInterface.sendTask(buildTask())
                        dialog.dismiss()
                    })
                .setNegativeButton(R.string.taskDialogCancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            it?.findViewById<TimePicker>(R.id.taskTimePicker)?.setIs24HourView(true)
            return builder.create()
        }
    }

    override fun onAttach(context: Context) {
        try {
            taskInterface = activity as AddTaskDialogInterface
        } catch (e: ClassCastException) {
            Log.e("INTERFACE", "onAttach: ClassCastException: ${e.message}")
        }
        super.onAttach(context)
    }

    fun loadViews(view : View){
        view.apply {
            timePicker = findViewById(R.id.taskTimePicker)
            datePicker = findViewById(R.id.taskDatePicker)
            taskSubjectEditText = findViewById(R.id.taskSubjectEditText)
            taskTitleEditText = findViewById(R.id.taskTitleEditText)
            taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText)
        }
    }

    fun buildTask() : Task{
        return Task(
            null,
            taskTitleEditText.text.toString(),
            taskDescriptionEditText.text.toString(),
            getDate(datePicker, timePicker),
            Subject(taskSubjectEditText.text.toString())
        )
    }

    private fun getDate(datePicker : DatePicker, timePicker: TimePicker) : Date{
        val calendar = GregorianCalendar()
        calendar.set(
            datePicker.year,datePicker.month,datePicker.dayOfMonth,
            timePicker.hour,
            timePicker.minute
        )
        return calendar.time
    }
}