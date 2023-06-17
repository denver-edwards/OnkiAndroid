package com.tappuri.onki

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.annotations.Nullable


class PhraseAdapter(context: Context?, phrases: List<String?>?) :
    ArrayAdapter<String?>(context!!, 0, phrases!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertedView = convertView
        val phrase = getItem(position)

        if (convertedView == null) {
            convertedView = LayoutInflater.from(context).inflate(R.layout.list_item_phrase, parent, false)
        }

        val textViewPhrase = convertedView!!.findViewById<TextView>(R.id.text_view_phrase)
        phrase?.let {
            textViewPhrase.text = it
        }

        convertedView.setOnClickListener {
            val intent = Intent(context, PhraseActivity::class.java)
            intent.putExtra("phraseName", phrase)
            context.startActivity(intent)
        }

        return convertedView
    }
}