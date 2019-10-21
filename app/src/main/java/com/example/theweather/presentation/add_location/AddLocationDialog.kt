package com.example.theweather.presentation.add_location

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.theweather.R
import kotlinx.android.synthetic.main.dialog_add_location.*


class AddLocationDialog : DialogFragment() {
    private var listener: OnAddLocationListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_add_location, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.apply {
                setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }

    private fun setClickListeners() {
        btn_add.setOnClickListener {
            if (!et_location_name.text.isNullOrEmpty()) {
                listener?.onAddClicked(et_location_name.text.toString())
                dismiss()
            }
        }

        btn_cancel.setOnClickListener { dismiss() }
    }

    fun setOnAddLocationListener(onAddLocationListener: OnAddLocationListener) {
        listener = onAddLocationListener
    }

    companion object {
        fun newInstance(): AddLocationDialog {
            return AddLocationDialog()
        }
    }
}