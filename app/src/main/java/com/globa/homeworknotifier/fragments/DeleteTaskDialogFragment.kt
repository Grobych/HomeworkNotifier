package com.globa.homeworknotifier.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.globa.homeworknotifier.R
import com.globa.homeworknotifier.interfaces.NoticeDialogListener

class DeleteTaskDialogFragment(private val listener : NoticeDialogListener) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity.let {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(R.string.delete_task_dialog_text)
                .setPositiveButton(R.string.yes
                ) { _, _ ->
                    listener.onDialogPositiveClick(this)
                }
                .setNegativeButton(R.string.cancel
                ) { _, _ ->
                    listener.onDialogNegativeClick(this)
                }
            return builder.create()
        }
    }
}