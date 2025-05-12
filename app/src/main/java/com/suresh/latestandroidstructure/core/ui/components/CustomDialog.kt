package com.suresh.latestandroidstructure.core.ui.components

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.ui.unit.dp
import com.suresh.latestandroidstructure.databinding.DialogCustomBinding

fun showCustomDialog(
    context: Context,
    title: String,
    message: String,
    positiveText: String = "OK",
    negativeText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null
) {
    val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context).setView(binding.root).create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    binding.tvTitle.text = title
    binding.tvMessage.text = message
    binding.btnPositive.text = positiveText

    if (negativeText != null) {
        binding.btnNegative.text = negativeText
        binding.btnNegative.visibility = View.VISIBLE
        binding.btnPositive.layoutParams = (binding.btnPositive.layoutParams as LinearLayout.LayoutParams).apply {
            marginEnd = 8
        }
    } else {
        binding.btnNegative.visibility = View.GONE
        binding.btnPositive.layoutParams = (binding.btnPositive.layoutParams as LinearLayout.LayoutParams).apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            marginEnd = 0
        }
    }

    binding.btnPositive.setOnClickListener {
        onPositiveClick?.invoke()
        dialog.dismiss()
    }

    binding.btnNegative.setOnClickListener {
        onNegativeClick?.invoke()
        dialog.dismiss()
    }

    dialog.show()
}