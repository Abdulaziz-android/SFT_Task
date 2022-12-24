package com.yakubjonov.sft_task.presentation.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.yakubjonov.sft_task.databinding.ItemDialogStateBinding

class StateDialog private constructor(internal var activity: Activity) {

    private var progressBar: ProgressBar? = null
    private var textView: TextView? = null
    internal var alertDialog: AlertDialog? = null

    fun show(loadingMessage: String? = "Подождите"): StateDialog {
        textView?.text = loadingMessage
        progressBar?.visibility = View.VISIBLE
        alertDialog!!.show()
        alertDialog?.setCancelable(false)
        return this
    }

    fun hide(): StateDialog {
        progressBar?.visibility = View.GONE
        alertDialog?.dismiss()
        return this
    }

    fun showError(errorMessage: String): StateDialog {
        progressBar?.visibility = View.GONE
        textView?.text = errorMessage
        alertDialog?.show()
        alertDialog?.setCancelable(true)
        return this
    }

    companion object {
        operator fun invoke(activity: Activity): StateDialog {
            val dialog = StateDialog(activity)
            val builder = AlertDialog.Builder(dialog.activity)
            val itemBinding = ItemDialogStateBinding.inflate(LayoutInflater.from(builder.context))
            dialog.progressBar = itemBinding.progressBar
            dialog.textView = itemBinding.textView
            builder.setView(itemBinding.root)
            builder.setCancelable(false)
            dialog.alertDialog = builder.create()
            return dialog
        }
    }
}