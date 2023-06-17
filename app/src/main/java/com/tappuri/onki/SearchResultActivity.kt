package com.tappuri.onki

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SearchResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SEARCH_RESULTS = "search_results"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val searchResults = intent.getStringArrayListExtra(EXTRA_SEARCH_RESULTS)
        val listView = findViewById<ListView>(R.id.listView)
        val emptyTextView = findViewById<TextView>(R.id.emptyTextView)
        val phraseList: List<String> =  searchResults?.filterNotNull() ?: emptyList()
        val adapter = PhraseAdapter(this, phraseList)

        listView.adapter = adapter
        listView.emptyView = emptyTextView

    }
}