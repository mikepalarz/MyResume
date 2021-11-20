package com.palarz.mike.myresume.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.widget.TextView
import com.palarz.mike.myresume.R
import java.lang.IllegalStateException

class ContactMeDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val rootView = it.layoutInflater.inflate(R.layout.dialog_contact_me, null)
            rootView.findViewById<ConstraintLayout>(R.id.dialog_contact_me_phone).setOnClickListener {
                val phoneNumber = getString(R.string.my_phone_number)
                val phoneIntent = Intent(Intent.ACTION_DIAL)
                phoneIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(phoneIntent)
            }
            rootView.findViewById<ConstraintLayout>(R.id.dialog_contact_me_email).setOnClickListener {
                val addresses = arrayOf(getString(R.string.my_email))
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    type = "*/*"
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, addresses)
                }
                if (emailIntent.resolveActivity(activity?.packageManager) != null) {
                    startActivity(emailIntent)
                }
            }
            rootView.findViewById<TextView>(R.id.dialog_contact_me_close).setOnClickListener {
                dismiss()
            }

            builder.setView(rootView)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}