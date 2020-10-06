package com.example.theweather.presentation.add_location

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.theweather.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_add_location.*


class AddLocationDialog : DialogFragment() {

    private lateinit var locationName: TextInputEditText
    private lateinit var submit: TextView
    private lateinit var cancel: TextView
    private var listener: OnAddLocationListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_add_location, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews()
        setClickListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private fun findViews() {
        submit = btn_add
        cancel = btn_cancel
        locationName = et_location_name
    }

    private fun setClickListeners() {
        submit.setOnClickListener {
            if (!locationName.text.isNullOrEmpty()) {
                listener?.onAddClicked(locationName.text.toString())
                dismiss()
            }
        }
        cancel.setOnClickListener {
            dismiss()
        }
    }

    fun setOnAddLocationListener(onAddLocationListener: OnAddLocationListener) {
        listener = onAddLocationListener
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null && dialog.window != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }

    }

    companion object {
        fun newInstance(): AddLocationDialog {
            return AddLocationDialog()
        }
    }
}